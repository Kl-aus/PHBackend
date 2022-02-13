package com.ph.phbackend.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "anamnesis")
public class Anamnesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anamnesis_id")
    private Long questionId;
    private String anamnesisCategory;
    private String question;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name="patient_id")
    private Patient patient;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name="diagnoses_id")
    private Set<Diagnose> diagnoses;

    public Anamnesis() {
    }

    public Anamnesis(Long questionId, String question, Patient patient, Set<Diagnose> diagnoses) {
        this.questionId = questionId;
        this.question = question;
        this.diagnoses = diagnoses;
        this.patient = patient;
    }

    public String getAnamnesisCategory() {
        return anamnesisCategory;
    }

    public void setAnamnesisCategory(String anamnesisCategory) {
        this.anamnesisCategory = anamnesisCategory;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long anamnesisId) {
        this.questionId = anamnesisId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoseSet) {
        this.diagnoses = diagnoseSet;
    }

    public Long getId() {
        return questionId;
    }

    public void setId(Long anamnesisId) {
        this.questionId = anamnesisId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}