package com.ph.phbackend.controllers;
import com.ph.phbackend.payload.request.PatientRequest;
import com.ph.phbackend.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/patient")
public class PatientController {
    PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getPe() {
        return ResponseEntity.ok(patientService.listPatients());
    }

    @PostMapping("/create")
    public ResponseEntity<?> postPe(@Valid @RequestBody PatientRequest patientRequest) {
        return ResponseEntity.ok(patientService.savePatient(patientRequest));
    }
}
