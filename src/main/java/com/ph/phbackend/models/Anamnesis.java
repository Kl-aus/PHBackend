package com.ph.phbackend.models;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "anamnesis")
public class Anamnesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anamnesis_id")
    private Long anamnesisId;
    private String anamnesisCategory;
    private String question;


    @OneToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @OneToMany
    @JoinColumn(name="diagnoses_id")
    private Set<Diagnose> diagnoseSet;

    public Anamnesis() {
    }

    public Anamnesis(Long anamnesisId, String question) {
        this.anamnesisId = anamnesisId;
        this.question = question;
    }

    public String getAnamnesisCategory() {
        return anamnesisCategory;
    }

    public void setAnamnesisCategory(String anamnesisCategory) {
        this.anamnesisCategory = anamnesisCategory;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getId() {
        return anamnesisId;
    }

    public void setId(Long anamnesisId) {
        this.anamnesisId = anamnesisId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}