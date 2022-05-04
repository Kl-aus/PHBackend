package com.ph.phbackend.models;
import java.io.Serializable;

public class RatingsId implements Serializable {
    private User user;
    private NursingRecommendation nursingRecommendation;

    public RatingsId() {
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getNursingRecommendation() {
        return nursingRecommendation;
    }

    public void setNursingRecommendation(Long nursingRecommendation) {
        this.nursingRecommendation = nursingRecommendation;
    }
}
