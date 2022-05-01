package com.ph.phbackend.models;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "diagnose")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnoses_id")
    private Long diagnosesId;
    @Column(name = "nursing_diagnoses", nullable = false)
    private String nursingDiagnoses;

    @Column(name = "nursing_diagnoses_category")
    private String nursingDiagnosesCategory;

    @Lob
    @Column(name = "nursing_diagnoses_description")
    private String nursingDiagnosesDescription;

    public Diagnose() {
    }

    public Diagnose(String nursingDiagnoses, String nursingDiagnosesDescription) {
        this.nursingDiagnoses = nursingDiagnoses;
        this.nursingDiagnosesDescription = nursingDiagnosesDescription;
    }

    public Diagnose(Long diagnosesId, String nursingDiagnoses, String nursingDiagnosesDescription, String nursingDiagnosesCategory) {
        this.diagnosesId = diagnosesId;
        this.nursingDiagnoses = nursingDiagnoses;
        this.nursingDiagnosesDescription = nursingDiagnosesDescription;
        this.nursingDiagnosesCategory = nursingDiagnosesCategory;
    }

    public String getNursingDiagnoses() {
        return nursingDiagnoses;
    }

    public void setNursingDiagnoses(String nursing_diagnoses_nanda) {
        this.nursingDiagnoses = nursing_diagnoses_nanda;
    }

    public String getNursingDiagnosesDescription() {
        return nursingDiagnosesDescription;
    }

    public void setNursingDiagnosesDescription(String nursingDiagnosesDescription) {
        this.nursingDiagnosesDescription = nursingDiagnosesDescription;
    }

    public String getNursingDiagnosesCategory() {
        return nursingDiagnosesCategory;
    }

    public void setNursingDiagnosesCategory(String nursingDiagnosesCategory) {
        this.nursingDiagnosesCategory = nursingDiagnosesCategory;
    }

    public Long getDiagnosesId() {
        return diagnosesId;
    }

    public void setDiagnosesId(Long diagnoses_id) {
        this.diagnosesId = diagnoses_id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Diagnose)) {
            return false;
        }
        Diagnose diagnose = (Diagnose) o;
        return diagnosesId == diagnose.diagnosesId &&
                Objects.equals(nursingDiagnoses, diagnose.nursingDiagnoses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diagnosesId, nursingDiagnoses);
    }
}
