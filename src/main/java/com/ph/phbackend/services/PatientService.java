package com.ph.phbackend.services;

import com.ph.phbackend.models.Diagnose;
import com.ph.phbackend.models.Patient;
import com.ph.phbackend.models.User;
import com.ph.phbackend.payload.request.DiagnosesRequest;
import com.ph.phbackend.payload.request.PatientRequest;
import com.ph.phbackend.repository.PatientRepository;
import com.ph.phbackend.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

@Service
public class PatientService {
    private PatientRepository patientRepository;
    private UserRepository userRepository;


    @Autowired
    public PatientService(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<Patient> listPatients() {
        return patientRepository.findAll();
    }

    @Transactional
    public Set<Patient> listPatientsById(long id) {
        Optional<User> user =  userRepository.findById(id);
        if(user.isPresent()) {
            Set<Patient> patients = user.get().getPatients();
            Hibernate.initialize(patients);
            return patients;
        } else {
            return null;
        }
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
            Hibernate.initialize(patients);
            patientRepository.save(patient);
            userRepository.save(user);
        });
        return patient;
    }

    @Transactional
    public void deletePatientsById(long patientId, long userId) {
        // To avoid Cascade delete & FK constraint errors delete relations manually
        Optional<Patient> patient =  patientRepository.findById(patientId);
        if(patient.isPresent()) {
            //Delete user-patient relation
            User user = userRepository.getById(userId);
            Set<Patient> patients = user.getPatients();
            for(Iterator<Patient> iterator = patients.iterator(); iterator.hasNext();) {
                Long thisPatientId = iterator.next().getPatientId();
                if (patientId == thisPatientId ) {
                    iterator.remove();
                }
            }
            user.setPatients(patients);
            userRepository.save(user);

            //clear patient-diagnoses relation
            Set<Diagnose> diagnoses = patient.get().getDiagnoses();
            diagnoses.clear();
            patient.get().setDiagnoses(diagnoses);
            patientRepository.save(patient.get());

            //Delete Patient
            patientRepository.delete(patient.get());
        }
    }
    @Transactional
    public void deletePatientDiagnoses(Set<Diagnose> diagnoses, long patientId) {
        System.out.println("DeletePatientDiagnoses();");
        Patient patient = patientRepository.getById(patientId);
        Set<Diagnose> patientDiagnoses = patient.getDiagnoses();
        System.out.println("PATIENTENDIAGNOSEN: " + patientDiagnoses.toString());
        System.out.println("LÖSCHDIAGNOSEN: " + diagnoses.toString());

        patientDiagnoses.removeAll(diagnoses);
        System.out.println("NACH LÖSCHEN: " + patientDiagnoses.toString());
        patient.setDiagnoses(patientDiagnoses);
        patientRepository.save(patient);
    }


}
