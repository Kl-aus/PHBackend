package com.ph.phbackend.services;

import com.ph.phbackend.models.*;
import com.ph.phbackend.payload.request.RecommendationRequest;
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
            this.nursingMeasureRepository.save(recommendation.getNursingMeasure());
            nursingMeasures.add(recommendation.getNursingMeasure());
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
            this.diagnoseRepository.save(recommendation.getDiagnose());
            diagnoses.add(recommendation.getDiagnose());
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

        Optional<User> user = this.userRepository.findById(recommendation.getUserId());
        if (user.isPresent()) {
            this.ratingsRepository.save(new Ratings(0, user.get(), thisRecommendation));
        } else {
            System.out.println("user not found");
            return null;
        }


        thisRecommendation.setSources(recommendation.getAuthor());
        thisRecommendation.setName(recommendation.getName());
        thisRecommendation.setDiagnosesMust(diagnoses);
        thisRecommendation.setNursingMeasureMust(nursingMeasures);

        try {
            this.nursingRecommendationRepository.save(thisRecommendation);
            return thisRecommendation;
        } catch (Exception e) {
            System.out.println("saving recommendation failed");
            e.printStackTrace();
            return null;
        }
    }
}
