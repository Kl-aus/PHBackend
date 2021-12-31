package com.ph.phbackend.models;
import javax.persistence.*;

@Entity
@Table(name = "diagnose")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnoses_id")
    private Long diagnosesId;
    @Column(name = "nursing_diagnoses_nanda", nullable = false)
    private String nursingDiagnosesNanda;


    public Diagnose() {
    }

    public Diagnose(Long diagnosesId, String nursingDiagnosesNanda) {
        this.diagnosesId = diagnosesId;
        this.nursingDiagnosesNanda = nursingDiagnosesNanda;
    }

    public String getNursingDiagnosesNanda() {
        return nursingDiagnosesNanda;
    }

    public void setNursingDiagnosesNanda(String nursing_diagnoses_nanda) {
        this.nursingDiagnosesNanda = nursing_diagnoses_nanda;
    }

    public Long getDiagnosesId() {
        return diagnosesId;
    }

    public void setDiagnosesId(Long diagnoses_id) {
        this.diagnosesId = diagnoses_id;
    }
}
