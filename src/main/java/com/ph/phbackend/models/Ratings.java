package com.ph.phbackend.models;

import javax.persistence.*;

@Entity
@IdClass(RatingsId.class)
@Table(name = "ratings")

public class Ratings {
    public Ratings(Integer rating, User user, NursingRecommendation nursingRecommendation) {
        this.rating = rating;
        this.user = user;
        this.nursingRecommendation = nursingRecommendation;
    }

    @Column(columnDefinition = "bigint default 0")
    Long rating;

    @Id
    @ManyToOne
    private User user;
    @Id
    @ManyToOne
    private NursingRecommendation nursingRecommendation;

    public Ratings() {
    }

    public Long getRatings() {
        return rating;
    }

    public void setRatings(Long ratings) {
        this.rating = ratings;
    }
}
