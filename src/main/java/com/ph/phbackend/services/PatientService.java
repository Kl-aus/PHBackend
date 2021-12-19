package com.ph.phbackend.services;

import com.ph.phbackend.models.Patient;
import com.ph.phbackend.payload.request.PatientRequest;
import com.ph.phbackend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional
    public List<Patient> listPatients() {
        return patientRepository.findAll();
    }

    @Transactional
    public Patient savePatient(PatientRequest patientRequest) {
        Patient patient = new Patient(
                patientRequest.getFirstName(),
                patientRequest.getLastName(),
                patientRequest.getWeight(),
                patientRequest.getHeight(),
                patientRequest.getAge(),
                patientRequest.getGender()
                );
        return patientRepository.save(patient);
    }
}
