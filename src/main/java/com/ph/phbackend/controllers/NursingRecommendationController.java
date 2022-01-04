package com.ph.phbackend.controllers;


import com.ph.phbackend.models.Diagnose;
import com.ph.phbackend.payload.request.DiagnosesRequest;
import com.ph.phbackend.services.NursingRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/recommendation")
public class NursingRecommendationController {

    @Autowired
    NursingRecommendationService nursingRecommendationService;

    public NursingRecommendationController() {
    }

    @PostMapping("/byDiagnose")
    public ResponseEntity<?> getRecommendationsByDiagnose(@Valid @RequestBody DiagnosesRequest diagnose) {
        return ResponseEntity.ok(nursingRecommendationService.getRecommendationsByDiagnose(diagnose.getDiagnose()));
    }

    @GetMapping("/setTest")
    public ResponseEntity<?> setTest() {
        return ResponseEntity.ok(nursingRecommendationService.setTestRecommendation());
    }

}
