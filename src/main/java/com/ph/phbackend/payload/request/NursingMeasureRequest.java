package com.ph.phbackend.payload.request;
import com.ph.phbackend.models.Images;
import com.ph.phbackend.models.NursingMeasure;

import java.awt.*;
import java.util.Set;

public class NursingMeasureRequest {
    private String careRecommendation;
    private String careRecommendationTitle;

    public NursingMeasureRequest() {
    }

    public NursingMeasureRequest(String careRecommendation, String careRecommendationTitle) {
        this.careRecommendation = careRecommendation;
        this.careRecommendationTitle = careRecommendationTitle;
    }

    public String getCareRecommendation() {
        return careRecommendation;
    }

    public void setCareRecommendation(String careRecommendation) {
        this.careRecommendation = careRecommendation;
    }

    public String getCareRecommendationTitle() {
        return careRecommendationTitle;
    }

    public void setCareRecommendationTitle(String careRecommendationTitle) {
        this.careRecommendationTitle = careRecommendationTitle;
    }

}
