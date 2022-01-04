package com.ph.phbackend.services;

import com.ph.phbackend.models.NursingMeasure;
import com.ph.phbackend.models.Diagnose;
import com.ph.phbackend.models.NursingRecommendation;
import com.ph.phbackend.payload.request.DiagnosesRequest;
import com.ph.phbackend.repository.NursingMeasureRepository;
import com.ph.phbackend.repository.DiagnoseRepository;
import com.ph.phbackend.repository.NursingRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;

import java.math.BigInteger;
import java.sql.Array;
import java.util.*;

@Service
public class NursingRecommendationService {
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;
    LocalContainerEntityManagerFactoryBean transactionManager;
    NursingRecommendationRepository nursingRecommendationRepository;
    DiagnoseRepository diagnoseRepository;
    NursingMeasureRepository nursingMeasureRepository;

    @Autowired
    public NursingRecommendationService(NursingRecommendationRepository nursingRecommendationRepository,
                                        DiagnoseRepository diagnoseRepository,
                                        NursingMeasureRepository nursingMeasureRepository,
                                        LocalContainerEntityManagerFactoryBean transactionManager) {
        this.nursingRecommendationRepository = nursingRecommendationRepository;
        this.diagnoseRepository = diagnoseRepository;
        this.nursingMeasureRepository = nursingMeasureRepository;
        this.transactionManager = transactionManager;
    }




    @Transactional
    public NursingRecommendation setTestRecommendation()  {
        NursingRecommendation recommendation = new NursingRecommendation();

        Diagnose diagnose = new Diagnose();
        diagnose.setNursingDiagnosesNanda("TestDiagnose2");
        this.diagnoseRepository.save(diagnose);

        Diagnose diagnose2 = new Diagnose();
        diagnose2.setNursingDiagnosesNanda("TestDiagnose3");
        this.diagnoseRepository.save(diagnose2);

        Diagnose diagnose3 = new Diagnose();
        diagnose3.setNursingDiagnosesNanda("TestDiagnose4");
        this.diagnoseRepository.save(diagnose3);

        Diagnose diagnose4 = new Diagnose();
        diagnose4.setNursingDiagnosesNanda("TestDiagnose5");
        this.diagnoseRepository.save(diagnose4);


        // add if does not exist, search and add if exists
        NursingMeasure measure = new NursingMeasure();
        measure.setCareRecommendation("Diese Pflegeempfehlung fügt der Kevin hinzu");
        measure.setCareRecommendationTitle("Kevin´s Pflegeempehlungs Titel");
        this.nursingMeasureRepository.save(measure);

        NursingMeasure measure2 = new NursingMeasure();
        measure2.setCareRecommendation("Diese Pflegeempfehlung fügt der Kevin hinzu");
        measure2.setCareRecommendationTitle("Kevin´s Pflegeempehlungs Titel3");
        this.nursingMeasureRepository.save(measure2);

        // add if does not exist, search and add if exists
        NursingMeasure dont = new NursingMeasure();
        dont.setCareRecommendation("Diese Pflegeempfehlung fügt der Kevin auch hinzu");
        dont.setCareRecommendationTitle("Kevin´s Pflegeempehlungs Titel2");
        this.nursingMeasureRepository.save(dont);

        Set<Diagnose> diagnoses = new HashSet<>();
        diagnoses.add(diagnose);
        diagnoses.add(diagnose2);
        diagnoses.add(diagnose3);
        diagnoses.add(diagnose4);

        recommendation.setDiagnosesMust(diagnoses);

        Set<NursingMeasure> nursingMeasureSetMust = new HashSet<>();
        nursingMeasureSetMust.add(measure);
        nursingMeasureSetMust.add(measure2);

        recommendation.setNursingMeasureMust(nursingMeasureSetMust);

        Set<NursingMeasure> nursingMeasureSetMustNot = new HashSet<>();
        nursingMeasureSetMustNot.add(dont);
        recommendation.setNursingMeasureMustNot(nursingMeasureSetMustNot);


        recommendation.setAuthor("PflegeHeute");
        recommendation.setName("Kevin");

        this.nursingRecommendationRepository.save(recommendation);
        return recommendation;
    }


    @Transactional
    public Set<NursingMeasure> getRecommendationsByDiagnose(Set<Diagnose> diagnoses) {
        Set<NursingMeasure> measures  = new HashSet<>();
        List<Long> diagnosesIdlist = new ArrayList<>();
        for (Diagnose diagnosis : diagnoses) {
            diagnosesIdlist.add(diagnosis.getDiagnosesId());
        }
        if (diagnosesIdlist.size() > 0) {
            /* Problem mit BIGINT gibt einmal Objektzurück einmal nicht --((BigInteger) reslutList.get(i)[0]).longValue() long recommendationId = Long.parseLong(resultList.get(i)[0].toString());
            Query: String q = "select nd.recommendation_id from nursing_diagnose nd group by nd.recommendation_id having group_concat(nd.diagnoses_id) in (?1)";

            Exception: java.lang.ClassCastException: class java.math.BigInteger cannot be cast to class [Ljava.lang.Object; (java.math.BigInteger and [Ljava.lang.Object; are in module java.base of loader 'bootstrap')
            */
            String q = "select nd.recommendation_id, group_concat(nd.diagnoses_id) as diagnoses from nursing_diagnose nd group by nd.recommendation_id having diagnoses in (?1)";
            Query query = entityManager.createNativeQuery(q);
            query.setParameter(1, diagnosesIdlist);

            try {
                List<Object[]> resultList = query.getResultList();
                for (int i = 0; i < resultList.size(); i++) {
                    System.out.println("RESULT LIST: " + resultList.get(i)[0]);
                    long recommendationId = Long.parseLong(resultList.get(i)[0].toString());
                    Optional<NursingRecommendation> nursingRecommendation = nursingRecommendationRepository.findById(recommendationId);
                    if(nursingRecommendation.isPresent()) {
                        measures = nursingRecommendation.get().getNursingMeasureMust();
                    } else {
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
}
