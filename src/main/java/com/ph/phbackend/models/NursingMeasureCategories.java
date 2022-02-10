package com.ph.phbackend.models;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "nursing_measure_categories")
public class NursingMeasureCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nursing_measure_category_id")
    private Long nursingMeasureCategoryId;
    private String nursingMeasureCategory;

    public NursingMeasureCategories() {
    }

    public NursingMeasureCategories(Long nursingMeasureCategoryId, String nursingMeasureCategory) {
        this.nursingMeasureCategoryId = nursingMeasureCategoryId;
        this.nursingMeasureCategory = nursingMeasureCategory;
    }

    public Long getNursingMeasureCategoryId() {
        return nursingMeasureCategoryId;
    }

    public void setNursingMeasureCategoryId(Long nursingMeasureCategoryId) {
        this.nursingMeasureCategoryId = nursingMeasureCategoryId;
    }

    public String getNursingMeasureCategory() {
        return nursingMeasureCategory;
    }

    public void setNursingMeasureCategory(String nursingMeasureCategory) {
        this.nursingMeasureCategory = nursingMeasureCategory;
    }
}
