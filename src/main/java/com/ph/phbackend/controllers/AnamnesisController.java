package com.ph.phbackend.controllers;

import com.ph.phbackend.payload.request.AnamnesisRequest;
import com.ph.phbackend.services.AnamnesisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/anamnesis")

public class AnamnesisController {
    AnamnesisService anamnesisService;

    public AnamnesisController(AnamnesisService anamnesisController) {
        this.anamnesisService = anamnesisController;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveAnamnesis(@Valid @RequestBody AnamnesisRequest anamnesisRequest) {
        return ResponseEntity.ok(anamnesisService.saveAnamnesis(anamnesisRequest));
    }

    @GetMapping("/getAnamnesis")
    public ResponseEntity<?> getAnamnesis() {
        return ResponseEntity.ok(anamnesisService.getAnamnesis());
    }
}
