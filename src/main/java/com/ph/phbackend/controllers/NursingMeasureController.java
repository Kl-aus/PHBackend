package com.ph.phbackend.controllers;


import com.ph.phbackend.payload.request.RecommendationRequest;
import com.ph.phbackend.services.NursingMeasureService;
import com.ph.phbackend.services.NursingRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/measure")
public class NursingMeasureController {

    @Autowired
    NursingMeasureService nursingMeasureService;

    public NursingMeasureController() {
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> getMeasures() {
        return ResponseEntity.ok(nursingMeasureService.getAllMeasures());
    }
}
