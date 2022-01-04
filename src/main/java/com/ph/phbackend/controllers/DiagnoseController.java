package com.ph.phbackend.controllers;

import com.ph.phbackend.payload.request.DiagnosesRequest;
import com.ph.phbackend.services.DiagnoseService;
import com.ph.phbackend.services.NursingRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/diagnoses")
public class DiagnoseController {
    DiagnoseService diagnoseService;
    NursingRecommendationService nursingRecommendationService;

    @Autowired
    public DiagnoseController(DiagnoseService diagnoseService, NursingRecommendationService nursingRecommendationService) {
        this.diagnoseService = diagnoseService;
        this.nursingRecommendationService = nursingRecommendationService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getPe() {
        return ResponseEntity.ok(diagnoseService.listDiagnoses());
    }


    @PostMapping("/savePatientDiagnoses")
    public ResponseEntity<?> savePatientDiagnoses(@Valid @RequestBody DiagnosesRequest diagnoses) {
        return ResponseEntity.ok(diagnoseService.savePatientDiagnoses(diagnoses));
    }

    @GetMapping("/getPatientDiagnoses")
    public ResponseEntity<?> getPatientDiagnoses(@Valid long selectedPatientId) {
        return ResponseEntity.ok(diagnoseService.getPatientDiagnoses(selectedPatientId));
    }

}
