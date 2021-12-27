package com.ph.phbackend.controllers;
import com.ph.phbackend.models.Patient;
import com.ph.phbackend.models.User;
import com.ph.phbackend.payload.request.PatientRequest;
import com.ph.phbackend.repository.UserRepository;
import com.ph.phbackend.services.PatientService;
import com.ph.phbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/patient")
public class PatientController {
    PatientService patientService;
    UserRepository userRepository;


    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getPe() {
        return ResponseEntity.ok(patientService.listPatients());
    }

    @PostMapping("/create")
    public ResponseEntity<?> postPe(@Valid @RequestBody PatientRequest patientRequest, long id) {
        Patient patient = new Patient(patientRequest.getFirstName(),
                patientRequest.getLastName(),
                patientRequest.getHeight(),
                patientRequest.getWeight(),
                patientRequest.getAge(),
                patientRequest.getGender());

        Set<Patient> patients = new HashSet<>(); // TODO: actually always just one
        patients.add(patient);

        userRepository.findById(id).ifPresent(user -> {
            user.setPatients(patients);
        });


        return ResponseEntity.ok(patientService.savePatient(patient));
    }
}
