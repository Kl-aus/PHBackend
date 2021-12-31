package com.ph.phbackend.payload.request;

import com.ph.phbackend.models.Diagnose;

import java.util.Set;

public class DiagnosesRequest {
    private Set<Diagnose> diagnose;
    private Long selectedPatientId;

    public DiagnosesRequest(Set<Diagnose> diagnose, Long selectedPatientId) {
        this.diagnose = diagnose;
        this.selectedPatientId = selectedPatientId;
    }

    public DiagnosesRequest() {
    }

    public Set<Diagnose> getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Set<Diagnose> diagnose) {
        this.diagnose = diagnose;
    }

    public Long getSelectedPatientId() {
        return selectedPatientId;
    }

    public void setSelectedPatientId(Long selectedPatientId) {
        this.selectedPatientId = selectedPatientId;
    }
}
