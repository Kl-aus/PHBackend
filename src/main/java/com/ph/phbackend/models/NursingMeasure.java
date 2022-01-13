package com.ph.phbackend.models;

import javax.persistence.*;

@Entity
@Table(name = "nursing_measure")
public class NursingMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nursing_measure_id", nullable = false)
    private Long recommendationId;
    @Lob
    @Column(name = "nursing_measure", nullable = true)
    private String careRecommendation;
    @Column(name = "nursing_measure_title", nullable = true)
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
