package com.ph.phbackend.controllers;


import com.ph.phbackend.payload.request.DiagnosesRequest;
import com.ph.phbackend.repository.RecommendationHelperRepository;
import com.ph.phbackend.services.RecommendationHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/recommendation")
public class RecommendationHelperController {

    @Autowired
    RecommendationHelperService recommendationHelperService;

    public RecommendationHelperController() {
    }

    @GetMapping("/byDiagnose")
    public ResponseEntity<?> getPatientsById(@Valid @RequestBody DiagnosesRequest diagnoses) {
        return ResponseEntity.ok(recommendationHelperService.getRecommendationsByDiagnose(diagnoses));
    }
}
