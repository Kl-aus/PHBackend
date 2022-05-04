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
    private String sources;

    @ManyToMany
    @JoinTable(name = "nursing_diagnose",
        joinColumns = @JoinColumn(name = "recommendation_id"),
        inverseJoinColumns = @JoinColumn(name = "diagnoses_id"))
    private Set<Diagnose> nursingDiagnose;

    @ManyToMany
    @JoinTable(name = "nursing_measure_must",
            joinColumns = @JoinColumn(name = "recommendation_id"),
            inverseJoinColumns = @JoinColumn(name = "nursing_measure_id"))
    private Set<NursingMeasure> nursingMeasureMust = new HashSet<>();

    @OneToMany(mappedBy = "nursingRecommendation")
    private Set<Ratings> ratingsSet = new HashSet<>();
    public NursingRecommendation() {
    }

    public NursingRecommendation(String name, String sources, Set<Diagnose> nursingDiagnose,
                                 Set<NursingMeasure> nursingMeasureMust) {
        this.name = name;
        this.sources = sources;
        this.nursingDiagnose = nursingDiagnose;
//      this.diagnoseMustNot = diagnoseMustNot;
//      this.nursingMeasureMustNot = nursingMeasureMustNot;
        this.nursingMeasureMust = nursingMeasureMust;
    }

    public Long getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(Long recommendationId) {
        this.recommendationId = recommendationId;
    }

    public Set<Diagnose> getNursingDiagnose() {
        return nursingDiagnose;
    }

    public void setNursingDiagnose(Set<Diagnose> nursingDiagnose) {
        this.nursingDiagnose = nursingDiagnose;
    }
//
//    public Long getRecHelperId() {
//        return recommendationId;
//    }
//
//    public void setRecHelperId(Long recHelperId) {
//        this.recommendationId = recHelperId;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public Collection<Diagnose> getDiagnosesMust() {
        return nursingDiagnose;
    }

    public void setDiagnosesMust(Set<Diagnose> diagnoseMust) {
        this.nursingDiagnose = diagnoseMust;
    }

    public Set<Ratings> getRatingsSet() {
        return ratingsSet;
    }

    public void setRatingsSet(Set<Ratings> ratingsSet) {
        this.ratingsSet = ratingsSet;
    }

    //    public Set<Diagnose> getDiagnosesMustNot() {
//        return diagnoseMustNot;
//    }
//
//    public void setDiagnosesMustNot(Set<Diagnose> diagnoseMustNot) {
//        this.diagnoseMustNot = diagnoseMustNot;
//    }
//
//    public Set<NursingMeasure> getNursingMeasureMustNot() {
//        return nursingMeasureMustNot;
//    }
//
//    public void setNursingMeasureMustNot(Set<NursingMeasure> recommendationsMustNot) {
//        this.nursingMeasureMustNot = recommendationsMustNot;
//    }

    public Set<NursingMeasure> getNursingMeasureMust() {
        return nursingMeasureMust;
    }

    public void setNursingMeasureMust(Set<NursingMeasure> recommendationsMust) {
        this.nursingMeasureMust = recommendationsMust;
    }

    @Override
    public String toString() {
        return "NursingRecommendation{" +
                "recommendationId=" + recommendationId +
                ", name='" + name + '\'' +
                ", sources='" + sources + '\'' +
                ", nursingDiagnose=" + nursingDiagnose +
                ", nursingMeasureMust=" + nursingMeasureMust +
                ", ratingsSet=" + ratingsSet +
                '}';
    }
}
