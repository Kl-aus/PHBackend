package com.ph.phbackend.models;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "nursing_measure")
public class NursingMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nursing_measure_id", nullable = false)
    private Long recommendationId;
    @Lob
    @Column(name = "nursing_measure")
    private String careRecommendation;
    @Column(name = "nursing_measure_title")
    private String careRecommendationTitle;
    @Column(name = "nursing_measure_category")
    private String nursingMeasureCategory;

    public NursingMeasure() {
    }

    public NursingMeasure(String careRecommendation, String careRecommendationTitle, String nursingMeasureCategory) {
        this.careRecommendation = careRecommendation;
        this.careRecommendationTitle = careRecommendationTitle;
        this.nursingMeasureCategory = nursingMeasureCategory;
    }

    @ManyToMany
    @JoinTable(name = "measure_image_relation",
            joinColumns = @JoinColumn(name = "nursing_measure_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Images> images = new HashSet<>();

    public String getNursingMeasureCategory() {
        return nursingMeasureCategory;
    }

    public void setNursingMeasureCategory(String nursingMeasureCategory) {
        this.nursingMeasureCategory = nursingMeasureCategory;
    }

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

    public Set<Images> getImages() {
        return images;
    }

    public void setImages(Set<Images> images) {
        this.images = images;
    }
}
