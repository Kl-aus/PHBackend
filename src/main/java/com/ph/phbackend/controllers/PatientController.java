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

    @Autowired
    PatientService patientService;
    @Autowired
    UserRepository userRepository;


    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getPatients() {
        return ResponseEntity.ok(patientService.listPatients());
    }

    @GetMapping("/byId")
    public ResponseEntity<?> getPatientsById(long id) {
        return ResponseEntity.ok(patientService.listPatientsById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody PatientRequest patientRequest) {
       return ResponseEntity.ok(patientService.createPatient(patientRequest));
    }
}
