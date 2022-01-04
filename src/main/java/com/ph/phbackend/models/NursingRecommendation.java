package com.ph.phbackend.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "nursing_recommendation")
public class NursingRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommendation_id")
    private Long recommendationId;
    private String name;
    private String author;

    @ManyToMany
    @JoinTable(name = "nursing_diagnose",
        joinColumns = @JoinColumn(name = "recommendation_id"),
        inverseJoinColumns = @JoinColumn(name = "diagnoses_id"))
    private Set<Diagnose> nursingDiagnose;



//    @ManyToMany
//    @JoinTable(name = "diagnose_must_not",
//            joinColumns = @JoinColumn(name = "rec_helper_id"),
//            inverseJoinColumns = @JoinColumn(name = "diagnoses_id"))
//    private Set<Diagnose> diagnoseMustNot = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "nursing_measure_must_not",
            joinColumns = @JoinColumn(name = "recommendation_id"),
            inverseJoinColumns = @JoinColumn(name = "nursing_measure_id"))
    private Set<NursingMeasure> nursingMeasureMustNot = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "nursing_measure_must",
            joinColumns = @JoinColumn(name = "recommendation_id"),
            inverseJoinColumns = @JoinColumn(name = "nursing_measure_id"))
    private Set<NursingMeasure> nursingMeasureMust = new HashSet<>();

    public NursingRecommendation() {
    }

    public NursingRecommendation(String name, String author, Set<Diagnose> nursingDiagnose,
                                 Set<NursingMeasure> nursingMeasureMust, Set<NursingMeasure> nursingMeasureMustNot) {
        this.name = name;
        this.author = author;
        this.nursingDiagnose = nursingDiagnose;
//        this.diagnoseMustNot = diagnoseMustNot;
        this.nursingMeasureMustNot = nursingMeasureMustNot;
        this.nursingMeasureMust = nursingMeasureMust;
    }

    public Long getRecHelperId() {
        return recommendationId;
    }

    public void setRecHelperId(Long recHelperId) {
        this.recommendationId = recHelperId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Collection<Diagnose> getDiagnosesMust() {
        return nursingDiagnose;
    }

    public void setDiagnosesMust(Set<Diagnose> diagnoseMust) {
        this.nursingDiagnose = diagnoseMust;
    }

//    public Set<Diagnose> getDiagnosesMustNot() {
//        return diagnoseMustNot;
//    }
//
//    public void setDiagnosesMustNot(Set<Diagnose> diagnoseMustNot) {
//        this.diagnoseMustNot = diagnoseMustNot;
//    }

    public Set<NursingMeasure> getNursingMeasureMustNot() {
        return nursingMeasureMustNot;
    }

    public void setNursingMeasureMustNot(Set<NursingMeasure> recommendationsMustNot) {
        this.nursingMeasureMustNot = recommendationsMustNot;
    }

    public Set<NursingMeasure> getNursingMeasureMust() {
        return nursingMeasureMust;
    }

    public void setNursingMeasureMust(Set<NursingMeasure> recommendationsMust) {
        this.nursingMeasureMust = recommendationsMust;
    }
}