package com.ph.phbackend.repository;

import com.ph.phbackend.models.Diagnose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnoseRepository extends JpaRepository<Diagnose, Long> {
}

