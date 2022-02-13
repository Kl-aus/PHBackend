package com.ph.phbackend.payload.request;

import com.ph.phbackend.models.Anamnesis;

public class AnamnesisRequest {

    private long patientId;
    private Anamnesis[] anamnesis;

    public AnamnesisRequest() {
    }

    public AnamnesisRequest(Long anamnesisId, String anamnesisCategory, String question, long patientId, Anamnesis anamnesis[]) {
        this.patientId = patientId;
        this.anamnesis = anamnesis;
    }

    public Anamnesis[] getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(Anamnesis[] anamnesis) {
        this.anamnesis = anamnesis;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }
}
