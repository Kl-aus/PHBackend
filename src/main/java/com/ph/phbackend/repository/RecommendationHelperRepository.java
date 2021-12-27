package com.ph.phbackend.repository;

import com.ph.phbackend.models.RecommendationHelper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationHelperRepository extends JpaRepository<RecommendationHelper, Long> {
}
