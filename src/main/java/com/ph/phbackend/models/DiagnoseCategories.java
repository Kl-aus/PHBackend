package com.ph.phbackend.models;

import javax.persistence.*;

@Entity
@Table(name = "diagnose_categories")
public class DiagnoseCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnose_category_id")
    private Long diagnoseCategoryId;
    private String diagnoseCategory;

    public DiagnoseCategories() {
    }

    public DiagnoseCategories(Long diagnoseCategoryId, String diagnoseCategory) {
        this.diagnoseCategoryId = diagnoseCategoryId;
        this.diagnoseCategory = diagnoseCategory;
    }

    public Long getDiagnoseCategoryId() {
        return diagnoseCategoryId;
    }

    public void setDiagnoseCategoryId(Long diagnoseCategoryId) {
        this.diagnoseCategoryId = diagnoseCategoryId;
    }

    public String getDiagnoseCategory() {
        return diagnoseCategory;
    }

    public void setDiagnoseCategory(String diagnoseCategory) {
        this.diagnoseCategory = diagnoseCategory;
    }
}
