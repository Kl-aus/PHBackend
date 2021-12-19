package com.ph.phbackend.models;

import javax.persistence.*;

@Entity
@Table(name = "care_recommendation")
public class CareRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long recommendationId;
    @Column(name = "care_recommendation", nullable = true, columnDefinition = "TEXT")
    private String careRecommendation;
    @Column(name = "care_recommendation_title", nullable = true)
    private String careRecommendationTitle;


    public String getCareRecommendation() {
        return careRecommendation;
    }

    public void setCareRecommendation(String care_recommendation) {
        this.careRecommendation = care_recommendation;
    }

    public String getCareRecommendationTitle() {
        return careRecommendationTitle;
    }

    public void setCareRecommendationTitle(String care_recommendation_title) {
        this.careRecommendationTitle = care_recommendation_title;
    }

    public Long getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(Long id) {
        this.recommendationId = id;
    }
}
