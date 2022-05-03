package com.ph.phbackend.payload.request;

import com.ph.phbackend.models.Diagnose;
import com.ph.phbackend.models.NursingMeasure;

public class RecommendationRequest {

    private String name;
    private String author;
    private Long userId;

    private Diagnose diagnose;

    private NursingMeasure nursingMeasure;

    public RecommendationRequest() {
    }

    public RecommendationRequest(String name, String author, Diagnose diagnose, NursingMeasure nursingMeasure, Long userId) {
        this.userId = userId;
        this.name = name;
        this.author = author;
        this.diagnose = diagnose;
        this.nursingMeasure = nursingMeasure;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    public NursingMeasure getNursingMeasure() {
        return nursingMeasure;
    }

    public void setNursingMeasure(NursingMeasure nursingMeasure) {
        this.nursingMeasure = nursingMeasure;
    }
}
