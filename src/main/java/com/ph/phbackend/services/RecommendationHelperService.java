package com.ph.phbackend.services;

import com.ph.phbackend.models.CareRecommendation;
import com.ph.phbackend.models.Diagnose;
import com.ph.phbackend.models.RecommendationHelper;
import com.ph.phbackend.payload.request.DiagnosesRequest;
import com.ph.phbackend.repository.CareRecommendationRepository;
import com.ph.phbackend.repository.DiagnoseRepository;
import com.ph.phbackend.repository.RecommendationHelperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecommendationHelperService {
    RecommendationHelperRepository recommendationHelperRepository;
    DiagnoseRepository diagnoseRepository;
    CareRecommendationRepository careRecommendationRepository;

    @Autowired
    public RecommendationHelperService(RecommendationHelperRepository recommendationHelperRepository,
                                       DiagnoseRepository diagnoseRepository,
                                       CareRecommendationRepository careRecommendationRepository) {
        this.recommendationHelperRepository = recommendationHelperRepository;
        this.diagnoseRepository = diagnoseRepository;
        this.careRecommendationRepository = careRecommendationRepository;
    }

    public void getHelpers(List<Long> musts, List<Long> mustNots) {
        //TODO diagnosen/recommendations filtern
    }

    @Transactional
    public void createNewRecommendationHelper(String name, String author, List<Long> diagnosesMust,
                                              List<Long> diagnosesMustNot, List<Long> recons, List<Long> reconsNot) {
        Set<Diagnose> musts = new HashSet<>();
        Set<Diagnose> mustNots = new HashSet<>();
        Set<CareRecommendation> recs = new HashSet<>();
        Set<CareRecommendation> recsNot = new HashSet<>();
        for (Long m: diagnosesMust) {
            musts.add(diagnoseRepository.findById(m).get());
        }
        for (Long m: diagnosesMustNot) {
            mustNots.add(diagnoseRepository.findById(m).get());
        }
        for (Long m: recons) {
            recs.add(careRecommendationRepository.findById(m).get());
        }
        for (Long m: reconsNot) {
            recsNot.add(careRecommendationRepository.findById(m).get());
        }
        this.recommendationHelperRepository.save(new RecommendationHelper(name, author, musts, mustNots, recs, recsNot));
    }

    @Transactional
    public void test(){
//        RecommendationResult recommendationResult = new RecommendationResult();
//        Diagnose diagnose = new Diagnose();
//        diagnose.setNursingDiagnosesNanda("Akute Verwirrung");
//        Diagnose diagnose2 = new Diagnose();
//        diagnose2.setNursingDiagnosesNanda("Blutdruck instabil hohes Risiko");
//
//        Set<Diagnose> diagnoseSet = new HashSet<>();
//        diagnoseSet.add(diagnose);
//
//        Set<Diagnose> diagnoseSet2 = new HashSet<>();
//        diagnoseSet2.add(diagnose2);
//
//        this.diagnosesRepository.save(diagnose);
//        this.diagnosesRepository.save(diagnose2);
//
//        recommendationResult.setAuthor("PflegeHeute");
//        recommendationResult.setName("Fronz");
//
//        recommendationResult.setDiagnosesMust(diagnoseSet);
//        recommendationResult.setDiagnosesMustNot(diagnoseSet2);
//        this.recommendationsHelperRepository.save(recommendationResult);
    }

    @Transactional
    public Set<CareRecommendation> getRecommendationsByDiagnose(DiagnosesRequest diagnoses) {
        return null;
    }
}
