package com.ph.phbackend.services;

import com.ph.phbackend.models.Anamnesis;
import com.ph.phbackend.models.Diagnose;
import com.ph.phbackend.models.Patient;
import com.ph.phbackend.payload.request.AnamnesisRequest;
import com.ph.phbackend.repository.AnamnesisRepository;
import com.ph.phbackend.repository.DiagnoseRepository;
import com.ph.phbackend.repository.PatientRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class AnamnesisService {
    private DiagnoseRepository diagnoseRepository;
    private PatientRepository patientRepository;
    private AnamnesisRepository anamnesisRepository;

    public AnamnesisService(DiagnoseRepository diagnoseRepository, PatientRepository patientRepository, AnamnesisRepository anamnesisRepository) {
        this.diagnoseRepository = diagnoseRepository;
        this.patientRepository = patientRepository;
        this.anamnesisRepository = anamnesisRepository;
    }

    @Transactional
    public Anamnesis saveAnamnesis(AnamnesisRequest anamnesisRequest) {
        Patient patient = this.patientRepository.getById(anamnesisRequest.getPatientId());
        Hibernate.initialize(patient);
        Anamnesis anamnesis = new Anamnesis();
        Hibernate.initialize(anamnesis);
        for (Anamnesis anamnesisQuestion: anamnesisRequest.getAnamnesis()) {
            anamnesis.setPatient(patient);
            anamnesis.setQuestion(anamnesisQuestion.getQuestion());
            anamnesis.setAnamnesisCategory(anamnesisQuestion.getAnamnesisCategory());
        }
        return this.anamnesisRepository.save(anamnesis);
    }

    @Transactional
    public List<Anamnesis> getAnamnesis() {
        return this.anamnesisRepository.findAll();
    }
}
