package com.ph.phbackend.controllers;
import com.ph.phbackend.payload.request.AnamnesisRequest;
import com.ph.phbackend.payload.request.DiagnosesRequest;
import com.ph.phbackend.payload.request.PatientRequest;
import com.ph.phbackend.repository.AnamnesisRepository;
import com.ph.phbackend.repository.UserRepository;
import com.ph.phbackend.services.AnamnesisService;
import com.ph.phbackend.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AnamnesisRepository anamnesisRepository;
    @Autowired
    AnamnesisService anamnesisService;


    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getPatients() {
        return ResponseEntity.ok(patientService.listPatients());
    }

    @GetMapping("/byId")
    public ResponseEntity<?> getPatientsById(@Valid long id) {
        return ResponseEntity.ok(patientService.listPatientsById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody PatientRequest patientRequest) {
       return ResponseEntity.ok(patientService.createPatient(patientRequest));
    }

    @DeleteMapping("/delete")
    public void deletePatientsById(@Valid @RequestBody PatientRequest patientRequest) {
        patientService.deletePatientsById(patientRequest.getPatientId(), patientRequest.getUserId());
    }

    @DeleteMapping("/deleteDiagnoses")
    public void deletePatientDiagnoses(@Valid @RequestBody DiagnosesRequest diagnosesRequest) {
        patientService.deletePatientDiagnoses(diagnosesRequest.getDiagnose(), diagnosesRequest.getSelectedPatientId());
    }

    @PostMapping("/saveAnamnesis")
    public ResponseEntity<?> saveAnamnesis(@Valid @RequestBody AnamnesisRequest anamnesisRequest) {
        return ResponseEntity.ok(patientService.saveAnamnesis(anamnesisRequest));
    }
}
