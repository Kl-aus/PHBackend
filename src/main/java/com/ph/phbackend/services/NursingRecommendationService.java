package com.ph.phbackend.services;

import com.ph.phbackend.models.NursingMeasure;
import com.ph.phbackend.models.Diagnose;
import com.ph.phbackend.models.NursingRecommendation;
import com.ph.phbackend.models.Patient;
import com.ph.phbackend.payload.request.RecommendationRequest;
import com.ph.phbackend.repository.NursingMeasureRepository;
import com.ph.phbackend.repository.DiagnoseRepository;
import com.ph.phbackend.repository.NursingRecommendationRepository;
import com.ph.phbackend.repository.PatientRepository;
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

    @Autowired
    public NursingRecommendationService(NursingRecommendationRepository nursingRecommendationRepository,
                                        DiagnoseRepository diagnoseRepository,
                                        NursingMeasureRepository nursingMeasureRepository,
                                        PatientRepository patientRepository,
                                        LocalContainerEntityManagerFactoryBean transactionManager) {
        this.nursingRecommendationRepository = nursingRecommendationRepository;
        this.diagnoseRepository = diagnoseRepository;
        this.nursingMeasureRepository = nursingMeasureRepository;
        this.transactionManager = transactionManager;
        this.patientRepository = patientRepository;
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
//                for (int i = 0; i < resultList.size(); i++) {
//                    long recommendationId = Long.parseLong(resultList.get(i)[0].toString());
//                    Optional<NursingRecommendation> nursingRecommendation = nursingRecommendationRepository.findById(recommendationId);
//                    Hibernate.initialize(nursingRecommendation);
//
//                   if (nursingRecommendation.isPresent()) {
//                        measures.addAll(nursingRecommendation.get().getNursingMeasureMust()); // get all measures from recommendation
//                        Hibernate.initialize(measures);
//                    } else {
//                        System.out.println("no recommendations found");
//                        return null;
//                    }
//                }
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
        this.nursingMeasureRepository.save(recommendation.getNursingMeasure());
        Diagnose diagnose = new Diagnose(recommendation.getNursingDiagnosesNanda(), recommendation.getNursingDiagnosesDescription());
        this.diagnoseRepository.save(diagnose);
        Set<Diagnose> thisDiagnose = new HashSet<>();
        thisDiagnose.add(diagnose);

        NursingRecommendation thisRecommendation = new NursingRecommendation();
        thisRecommendation.setAuthor(recommendation.getAuthor());
        thisRecommendation.setName(recommendation.getName());
        thisRecommendation.setDiagnosesMust(thisDiagnose);
        Set<NursingMeasure> nursingMeasures = new HashSet<>();
        nursingMeasures.add(recommendation.getNursingMeasure());
        thisRecommendation.setNursingMeasureMust(nursingMeasures);
        //thisRecommendation.setNursingMeasureMustNot();

        try {
            this.nursingRecommendationRepository.save(thisRecommendation);
            return thisRecommendation;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
