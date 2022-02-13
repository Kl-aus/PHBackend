package com.ph.phbackend.repository;

import com.ph.phbackend.models.Anamnesis;
import com.ph.phbackend.models.NursingMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnamnesisRepository extends JpaRepository<Anamnesis, Long> {

}
