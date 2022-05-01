package com.ph.phbackend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@IdClass(RatingsId.class)
@Table(name = "ratings")

public class Ratings {
    public Ratings(int rating, User user, NursingRecommendation nursingRecommendation) {
        this.rating = rating;
        this.user = user;
        this.nursingRecommendation = nursingRecommendation;
    }

    @Column(name = "rating")
    int rating;

    @Id
    @ManyToOne
    private User user;
    @Id
    @ManyToOne
    private NursingRecommendation nursingRecommendation;

    public Ratings() {

    }

    public int getRatings() {
        return rating;
    }

    public void setRatings(int ratings) {
        this.rating = ratings;
    }
}
