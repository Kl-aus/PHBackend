package com.ph.phbackend.services;

import com.ph.phbackend.models.*;
import com.ph.phbackend.payload.request.RecommendationRequest;
import com.ph.phbackend.payload.response.RecommendationResponse;
import com.ph.phbackend.repository.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;

import java.util.*;

@Service
public class NursingRecommendationService {
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;
    LocalContainerEntityManagerFactoryBean transactionManager;
    NursingRecommendationRepository nursingRecommendationRepository;
    DiagnoseRepository diagnoseRepository;
    NursingMeasureRepository nursingMeasureRepository;
    PatientRepository patientRepository;
    RatingsRepository ratingsRepository;
    UserRepository userRepository;

    @Autowired
    public NursingRecommendationService(NursingRecommendationRepository nursingRecommendationRepository,
                                        DiagnoseRepository diagnoseRepository,
                                        NursingMeasureRepository nursingMeasureRepository,
                                        PatientRepository patientRepository,
                                        LocalContainerEntityManagerFactoryBean transactionManager,
                                        RatingsRepository ratingsRepository,
                                        UserRepository userRepository) {
        this.nursingRecommendationRepository = nursingRecommendationRepository;
        this.diagnoseRepository = diagnoseRepository;
        this.nursingMeasureRepository = nursingMeasureRepository;
        this.transactionManager = transactionManager;
        this.patientRepository = patientRepository;
        this.ratingsRepository = ratingsRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Set<NursingMeasure> getRecommendationsByPatient(long id) {
        Set<NursingMeasure> measures  = new HashSet<>();
        Hibernate.initialize(measures);
        Set<Diagnose> diagnoses = null;

        Optional<Patient> patient =  patientRepository.findById(id);
        if(patient.isPresent()) {
            diagnoses = patient.get().getDiagnoses();
            Hibernate.initialize(diagnoses);
        }

        List<Long> diagnosesIdlist = new ArrayList<>();
        if (diagnoses != null) {
            for (Diagnose diagnosis : diagnoses) {
                diagnosesIdlist.add(diagnosis.getDiagnosesId());
            }
        }

        if (diagnosesIdlist.size() > 0) {
            //String q = "select nd.recommendation_id, group_concat(nd.diagnoses_id) as diagnoses from nursing_diagnose nd group by nd.recommendation_id having diagnoses in (?1)";
            //select unique nursing_measure_id from nursing_measure_must where recommendation_id in(select unique recommendation_id from nursing_diagnose where diagnoses_id in (97,3)) not in ...
            String q = "select distinct nursing_measure_id from nursing_measure_must where recommendation_id in(select distinct recommendation_id from nursing_diagnose where diagnoses_id in (?1))";
            Query query = entityManager.createNativeQuery(q);
            query.setParameter(1, diagnosesIdlist);

            try {
                List<Object[]> resultList = query.getResultList();
                for (int i = 0; i < resultList.size(); i++) {
                    long nursingMeasureId = Long.parseLong(String.valueOf(resultList.get(i)));
                    Optional<NursingMeasure> nursingMeasure = nursingMeasureRepository.findById(nursingMeasureId);
                    if (nursingMeasure.isPresent()) {
                        Hibernate.initialize(nursingMeasure.get().getImages());
                        measures.add(nursingMeasure.get()); // get all measures from recommendation
                    } else {
                        System.out.println("recommendation not found");
                        return null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return measures;
        } else {
            System.out.println("diagnoses empty!!");
            return  null;
        }
    }

    @Transactional
    public Object saveRecommendation(RecommendationRequest recommendation) {
        NursingRecommendation thisRecommendation = new NursingRecommendation();
        Set<NursingMeasure> nursingMeasures = new HashSet<>();
        Set<Diagnose> diagnoses = new HashSet<>();

        if(recommendation.getNursingMeasure().getRecommendationId() < 1) {
            nursingMeasures.add( this.nursingMeasureRepository.save(recommendation.getNursingMeasure()));
        } else {
            Optional<NursingMeasure> nursingMeasure = nursingMeasureRepository.findById(recommendation.getNursingMeasure().getRecommendationId());
            if (nursingMeasure.isPresent()) {
                Hibernate.initialize(nursingMeasure.get().getImages());
                nursingMeasures.add(nursingMeasure.get());
            } else {
                System.out.println("nursing measure not found");
                return null;
            }
        }

        if(recommendation.getDiagnose().getDiagnosesId() < 1) {
            diagnoses.add(this.diagnoseRepository.save(recommendation.getDiagnose()));
        } else {
            Optional<Diagnose> diagnose = diagnoseRepository.findById(recommendation.getDiagnose().getDiagnosesId());
            if (diagnose.isPresent()) {
                Hibernate.initialize(diagnose.get());
                diagnoses.add(diagnose.get());
            } else {
                System.out.println("diagnose not found");
                return null;
            }
        }

        thisRecommendation.setSources(recommendation.getAuthor());
        thisRecommendation.setName(recommendation.getName());
        thisRecommendation.setDiagnosesMust(diagnoses);
        thisRecommendation.setNursingMeasureMust(nursingMeasures);

        try {
            NursingRecommendation rec = this.nursingRecommendationRepository.save(thisRecommendation);
            Optional<User> user = this.userRepository.findById(recommendation.getUserId());
            if (user.isPresent()) {
                this.ratingsRepository.save(new Ratings(0L, user.get().getUserId(), rec.getRecommendationId()));
            } else {
                System.out.println("user not found");
                return null;
            }
            return rec;
        } catch (Exception e) {
            System.out.println("saving recommendation failed");
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public Set<RecommendationResponse> getRecommendations() {
        Set<RecommendationResponse> recommendationResponses = new HashSet<>();
        List<Long> ratings = new ArrayList<>();

        String q = "select nr.recommendation_id, nr.sources, nr.name, nm.nursing_measure_id, nm.nursing_measure_title, d.diagnoses_id, d.nursing_diagnoses from nursing_recommendation nr\n" +
                "inner join nursing_measure_must nmm on nr.recommendation_id = nmm.recommendation_id\n" +
                "inner join nursing_measure nm on nmm.nursing_measure_id = nm.nursing_measure_id \n" +
                "inner join nursing_diagnose nd on nd.recommendation_id = nr.recommendation_id\n" +
                "inner join diagnose d on nd.diagnoses_id = d.diagnoses_id";

        String q2 = "select rating from ratings where nursingRecommendation = (?1)";

        Query recQuery = entityManager.createNativeQuery(q);
        Query ratingsQuery = entityManager.createNativeQuery(q2);

        try {
            List<Object[]> recList = recQuery.getResultList();
            for(Object[] recObject : recList) {
                RecommendationResponse response = new RecommendationResponse();
                response.setRecId(((BigInteger) recObject[0]).longValue());

                ratingsQuery.setParameter(1, ((BigInteger) recObject[0]).longValue());
                List<Object> ratingsList = ratingsQuery.getResultList();
                for(Object rating : ratingsList) {
                    ratings.add(((BigInteger) rating).longValue());
                }
                OptionalDouble average = ratings.stream().mapToDouble(a->a).average();
                if (average.isPresent()) {
                    double d = Math.round(average.getAsDouble()*10)/10.0;
                    response.setRating(d);
                } else {
                    response.setRating(0.0);
                }

                response.setSources((String) recObject[1]);
                response.setName((String) recObject[2]);
                response.setMeasureId(((BigInteger) recObject[3]).longValue());
                response.setMeasureTitle((String) recObject[4]);
                response.setDiagnoseId(((BigInteger) recObject[5]).longValue());
                response.setDiagnoseTitle((String) recObject[6]);
                recommendationResponses.add(response);
            }
            return  recommendationResponses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
