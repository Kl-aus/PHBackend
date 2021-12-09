package com.ph.phbackend.repository;

import com.ph.phbackend.models.CareRecommendation;
import com.ph.phbackend.models.RecommendationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RecommendationHelperRepository extends JpaRepository<RecommendationResult, Long> {
}
