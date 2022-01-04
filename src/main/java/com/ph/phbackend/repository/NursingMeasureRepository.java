package com.ph.phbackend.repository;

import com.ph.phbackend.models.NursingMeasure;
import com.ph.phbackend.models.NursingRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NursingMeasureRepository extends JpaRepository<NursingMeasure, Long> {

}
