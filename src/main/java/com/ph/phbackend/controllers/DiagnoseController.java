package com.ph.phbackend.controllers;

import com.ph.phbackend.services.DiagnoseService;
import com.ph.phbackend.services.RecommendationHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/diagnoses")
public class DiagnoseController {
    DiagnoseService diagnoseService;
    RecommendationHelperService recommendationHelperService;

    @Autowired
    public DiagnoseController(DiagnoseService diagnoseService, RecommendationHelperService recommendationHelperService) {
        this.diagnoseService = diagnoseService;
        this.recommendationHelperService = recommendationHelperService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getPe() {
        return ResponseEntity.ok(diagnoseService.listDiagnoses());
    }

    @GetMapping("/test")
    public ResponseEntity<?> getTest() {
        this.recommendationHelperService.test();
        return ResponseEntity.ok(diagnoseService.listDiagnoses());
    }
}
