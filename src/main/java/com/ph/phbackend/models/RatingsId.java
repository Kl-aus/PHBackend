package com.ph.phbackend.models;
import java.io.Serializable;

public class RatingsId implements Serializable {
    private User user;
    private NursingRecommendation nursingRecommendation;

    public RatingsId() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NursingRecommendation getNursingRecommendation() {
        return nursingRecommendation;
    }

    public void setNursingRecommendation(NursingRecommendation nursingRecommendation) {
        this.nursingRecommendation = nursingRecommendation;
    }
}
