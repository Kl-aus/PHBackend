package com.ph.phbackend.payload.request;

public class DiagnosesRequest {
    private Long diagnoses_id;
    private String nursing_diagnoses_nanda;

    public String getNursing_diagnoses_nanda() {
        return nursing_diagnoses_nanda;
    }

    public void setNursing_diagnoses_nanda(String nursing_diagnoses_nanda) {
        this.nursing_diagnoses_nanda = nursing_diagnoses_nanda;
    }

    public Long getDiagnoses_id() {
        return diagnoses_id;
    }

    public void setDiagnoses_id(Long diagnoses_id) {
        this.diagnoses_id = diagnoses_id;
    }
}
