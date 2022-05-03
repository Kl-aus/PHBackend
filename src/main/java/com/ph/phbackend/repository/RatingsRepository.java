package com.ph.phbackend.repository;

import com.ph.phbackend.models.Ratings;
import com.ph.phbackend.models.RatingsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings, RatingsId> {
    @Override
    Optional<Ratings> findById(RatingsId ratingsId);

}
