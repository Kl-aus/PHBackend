package com.ph.phbackend.repository;

import com.ph.phbackend.models.CareRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareRecommendationRepository extends JpaRepository<CareRecommendation, Long> {
        }
