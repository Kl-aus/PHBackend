package com.ph.phbackend.payload.response;

public class RecommendationResponse {
    private String diagnoseTitle;
    private Long diagnoseId;
    private String measureTitle;
    private Long measureId;

    private Long recId;
    private String name;
    private String sources;

    private Double rating;

    public RecommendationResponse() {
    }

    public RecommendationResponse(String diagnoseTitle, Long diagnoseId, String measureTitle, Long measureId, Long recId, String name, String sources, Double rating) {
        this.diagnoseTitle = diagnoseTitle;
        this.diagnoseId = diagnoseId;
        this.measureTitle = measureTitle;
        this.measureId = measureId;
        this.recId = recId;
        this.name = name;
        this.sources = sources;
        this.rating = rating;
    }

    public String getDiagnoseTitle() {
        return diagnoseTitle;
    }

    public void setDiagnoseTitle(String diagnoseTitle) {
        this.diagnoseTitle = diagnoseTitle;
    }

    public Long getDiagnoseId() {
        return diagnoseId;
    }

    public void setDiagnoseId(Long diagnoseId) {
        this.diagnoseId = diagnoseId;
    }

    public String getMeasureTitle() {
        return measureTitle;
    }

    public void setMeasureTitle(String measureTitle) {
        this.measureTitle = measureTitle;
    }

    public Long getMeasureId() {
        return measureId;
    }

    public void setMeasureId(Long measureId) {
        this.measureId = measureId;
    }

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
