package com.ph.phbackend.repository;

import com.ph.phbackend.models.Diagnose;
import com.ph.phbackend.models.NursingRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface NursingRecommendationRepository extends JpaRepository<NursingRecommendation, Long> {
    //NursingRecommendation findNursingRecommendationByNursingDiagnoseIn(Collection<Set<Diagnose>> nursingDiagnose);
    Optional<NursingRecommendation> findNursingRecommendationByNursingDiagnoseIn(Collection<Diagnose> nursingDiagnose);
}
