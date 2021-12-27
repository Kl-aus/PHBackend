package com.ph.phbackend.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recommendation_result")
public class RecommendationHelper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_helper_id")//richtige ID
    private Long recHelperId;
    private String name;
    private String author;


    @ManyToMany
    @JoinTable(name = "diagnose_recommendation_must",
        joinColumns = @JoinColumn(name = "recommendation_id"),
        inverseJoinColumns = @JoinColumn(name = "diagnoses_id"))
    private Set<Diagnose> diagnoseMust = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "diagnose_recommendation_must_not",
            joinColumns = @JoinColumn(name = "recommendation_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnoses_id"))
    private Set<Diagnose> diagnoseMustNot = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "recommendation_must_not",
            joinColumns = @JoinColumn(name = "recommendation_result_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnoses_result_id"))
    private Set<CareRecommendation> recommendationsMustNot = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "recommendation_must",
            joinColumns = @JoinColumn(name = "recommendation_result_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnoses_result_id"))
    private Set<CareRecommendation> recommendationsMust = new HashSet<>();

    public RecommendationHelper() {
    }

    public RecommendationHelper(String name, String author, Set<Diagnose> diagnoseMust, Set<Diagnose> diagnoseMustNot,
                                Set<CareRecommendation> careRecommendationMust, Set<CareRecommendation> careRecommendationMustNot) {
        this.name = name;
        this.author = author;
        this.diagnoseMust = diagnoseMust;
        this.diagnoseMustNot = diagnoseMustNot;
        this.recommendationsMustNot = careRecommendationMustNot;
        this.recommendationsMust = careRecommendationMust;
    }

    public Long getRecHelperId() {
        return recHelperId;
    }

    public void setRecHelperId(Long recHelperId) {
        this.recHelperId = recHelperId;
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

    public Set<Diagnose> getDiagnosesMust() {
        return diagnoseMust;
    }

    public void setDiagnosesMust(Set<Diagnose> diagnoseMust) {
        this.diagnoseMust = diagnoseMust;
    }

    public Set<Diagnose> getDiagnosesMustNot() {
        return diagnoseMustNot;
    }

    public void setDiagnosesMustNot(Set<Diagnose> diagnoseMustNot) {
        this.diagnoseMustNot = diagnoseMustNot;
    }

    public Set<CareRecommendation> getRecommendationsMustNot() {
        return recommendationsMustNot;
    }

    public void setRecommendationsMustNot(Set<CareRecommendation> recommendationsMustNot) {
        this.recommendationsMustNot = recommendationsMustNot;
    }

    public Set<CareRecommendation> getRecommendationsMust() {
        return recommendationsMust;
    }

    public void setRecommendationsMust(Set<CareRecommendation> recommendationsMust) {
        this.recommendationsMust = recommendationsMust;
    }
}
