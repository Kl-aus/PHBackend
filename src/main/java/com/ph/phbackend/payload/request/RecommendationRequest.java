package com.ph.phbackend.payload.request;

import com.ph.phbackend.models.NursingMeasure;

import java.util.Set;

public class RecommendationRequest {

    private String name;
    private String author;

    private String nursingDiagnosesNanda;
    private String nursingDiagnosesDescription;

    private NursingMeasure nursingMeasure;

    public RecommendationRequest() {
    }

    public RecommendationRequest(String name, String author, String nursingDiagnosesNanda, String nursingDiagnosesDescription, NursingMeasure nursingMeasure) {
        this.name = name;
        this.author = author;
        this.nursingDiagnosesNanda = nursingDiagnosesNanda;
        this.nursingDiagnosesDescription = nursingDiagnosesDescription;
        this.nursingMeasure = nursingMeasure;
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

    public String getNursingDiagnosesNanda() {
        return nursingDiagnosesNanda;
    }

    public void setNursingDiagnosesNanda(String nursingDiagnosesNanda) {
        this.nursingDiagnosesNanda = nursingDiagnosesNanda;
    }

    public String getNursingDiagnosesDescription() {
        return nursingDiagnosesDescription;
    }

    public void setNursingDiagnosesDescription(String nursingDiagnosesDescription) {
        this.nursingDiagnosesDescription = nursingDiagnosesDescription;
    }

    public NursingMeasure getNursingMeasure() {
        return nursingMeasure;
    }

    public void setNursingMeasure(NursingMeasure nursingMeasure) {
        this.nursingMeasure = nursingMeasure;
    }
}
