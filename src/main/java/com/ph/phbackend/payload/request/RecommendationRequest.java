package com.ph.phbackend.payload.request;

import com.ph.phbackend.models.Diagnose;
import com.ph.phbackend.models.NursingMeasure;
import com.ph.phbackend.models.NursingRecommendation;

import java.util.Set;

public class RecommendationRequest {

    private String name;
    private String author;

    private String nursingDiagnosesNanda;
    private String nursingDiagnosesDescription;

    private Set<NursingMeasure> nursingMeasures;

    public RecommendationRequest() {
    }

    public RecommendationRequest(String name, String author, String nursingDiagnosesNanda, String nursingDiagnosesDescription, Set<NursingMeasure> nursingMeasures) {
        this.name = name;
        this.author = author;
        this.nursingDiagnosesNanda = nursingDiagnosesNanda;
        this.nursingDiagnosesDescription = nursingDiagnosesDescription;
        this.nursingMeasures = nursingMeasures;
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

    public Set<NursingMeasure> getNursingMeasures() {
        return nursingMeasures;
    }

    public void setNursingMeasures(Set<NursingMeasure> nursingMeasures) {
        this.nursingMeasures = nursingMeasures;
    }
}
