package com.ph.phbackend.controllers;


import com.ph.phbackend.payload.request.RecommendationRequest;
import com.ph.phbackend.services.NursingRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/recommendation")
public class NursingRecommendationController {

    @Autowired
    NursingRecommendationService nursingRecommendationService;

    public NursingRecommendationController() {
    }

    @GetMapping("/byPatient")
    public ResponseEntity<?> getRecommendationsByPatient(@Valid long patientId) {
        return ResponseEntity.ok(nursingRecommendationService.getRecommendationsByPatient(patientId));
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> saveRecommendation(@Valid @RequestBody RecommendationRequest recommendation) {
        return ResponseEntity.ok(nursingRecommendationService.saveRecommendation(recommendation));
    }
}
