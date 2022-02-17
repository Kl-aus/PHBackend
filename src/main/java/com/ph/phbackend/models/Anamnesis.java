package com.ph.phbackend.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "anamnesis")
public class Anamnesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;
    private String anamnesisCategory;
    private String question;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "anamnesis_patient_relation",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private Set<Patient> patients = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "anamnesis_diagnoses_relation",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnoses_id"))
    private Set<Diagnose> diagnoses = new HashSet<>();

    public Anamnesis() {
    }

    public Anamnesis(Long questionId, String question, Set<Patient> patients, Set<Diagnose> diagnoses) {
        this.questionId = questionId;
        this.question = question;
        this.diagnoses = diagnoses;
        this.patients = patients;
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

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
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