package com.ph.phbackend.repository;

import com.ph.phbackend.models.Diagnose;
import com.ph.phbackend.models.NursingMeasure;
import com.ph.phbackend.models.NursingRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface NursingMeasureRepository extends JpaRepository<NursingMeasure, Long> {
    Optional<NursingMeasure> findNursingMeasureByCareRecommendationTitle(String string);
}
