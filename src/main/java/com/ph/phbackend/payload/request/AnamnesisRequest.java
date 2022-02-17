package com.ph.phbackend.payload.request;

import com.ph.phbackend.models.Anamnesis;

import java.util.Set;

public class AnamnesisRequest {

    private long patientId;
    private Set<Anamnesis> anamnesis;

    public AnamnesisRequest() {
    }

    public AnamnesisRequest(Long anamnesisId, String anamnesisCategory, String question, long patientId, Set<Anamnesis> anamnesis) {
        this.patientId = patientId;
        this.anamnesis = anamnesis;
    }

    public Set<Anamnesis> getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(Set<Anamnesis> anamnesis) {
        this.anamnesis = anamnesis;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }
}
