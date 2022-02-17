package com.ph.phbackend.services;

import com.ph.phbackend.models.Anamnesis;
import com.ph.phbackend.models.Diagnose;
import com.ph.phbackend.models.Patient;
import com.ph.phbackend.payload.request.AnamnesisRequest;
import com.ph.phbackend.repository.AnamnesisRepository;
import com.ph.phbackend.repository.DiagnoseRepository;
import com.ph.phbackend.repository.PatientRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AnamnesisService {
    private DiagnoseRepository diagnoseRepository;
    private final PatientRepository patientRepository;
    private final AnamnesisRepository anamnesisRepository;

    @Autowired
    public AnamnesisService(DiagnoseRepository diagnoseRepository, PatientRepository patientRepository, AnamnesisRepository anamnesisRepository) {
        this.diagnoseRepository = diagnoseRepository;
        this.patientRepository = patientRepository;
        this.anamnesisRepository = anamnesisRepository;
    }

    @Transactional
    public List<Anamnesis> getAnamnesis() {
        return this.anamnesisRepository.findAll();
    }
}
