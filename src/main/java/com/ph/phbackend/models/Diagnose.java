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
    @Column(name = "nursing_diagnoses_nanda", nullable = false)
    private String nursingDiagnosesNanda;
    @Lob
    @Column(name = "nursing_diagnoses_description")
    private String nursingDiagnosesDescription;

    public Diagnose() {
    }

    public Diagnose(String nursingDiagnosesNanda, String nursingDiagnosesDescription) {
        this.nursingDiagnosesNanda = nursingDiagnosesNanda;
        this.nursingDiagnosesDescription = nursingDiagnosesDescription;
    }

    public Diagnose(Long diagnosesId, String nursingDiagnosesNanda, String nursingDiagnosesDescription) {
        this.diagnosesId = diagnosesId;
        this.nursingDiagnosesNanda = nursingDiagnosesNanda;
        this.nursingDiagnosesDescription = nursingDiagnosesDescription;
    }

    public String getNursingDiagnosesNanda() {
        return nursingDiagnosesNanda;
    }

    public void setNursingDiagnosesNanda(String nursing_diagnoses_nanda) {
        this.nursingDiagnosesNanda = nursing_diagnoses_nanda;
    }

    public String getNursingDiagnosesDescription() {
        return nursingDiagnosesDescription;
    }

    public void setNursingDiagnosesDescription(String nursingDiagnosesDescription) {
        this.nursingDiagnosesDescription = nursingDiagnosesDescription;
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
                Objects.equals(nursingDiagnosesNanda, diagnose.nursingDiagnosesNanda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diagnosesId, nursingDiagnosesNanda);
    }


}
