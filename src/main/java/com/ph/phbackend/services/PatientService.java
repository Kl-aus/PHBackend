package com.ph.phbackend.services;

import com.ph.phbackend.models.Patient;
import com.ph.phbackend.models.User;
import com.ph.phbackend.payload.request.PatientRequest;
import com.ph.phbackend.repository.PatientRepository;
import com.ph.phbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Set<Patient> listPatientsById(long id) {
        Optional<User> user =  userRepository.findById(id);
        return user.map(User::getPatients).orElse(null);
    }

    @Transactional
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient createPatient(PatientRequest patientRequest) {

        Patient patient = new Patient(patientRequest.getFirstName(),
                patientRequest.getLastName(),
                patientRequest.getHeight(),
                patientRequest.getWeight(),
                patientRequest.getAge(),
                patientRequest.getGender());

        userRepository.findById(patientRequest.getUserId()).ifPresent(user -> {
            Set<Patient> patients = user.getPatients();
            patients.add(patient);
            patientRepository.save(patient);
            userRepository.save(user);
        });

        return patient;
    }



}
