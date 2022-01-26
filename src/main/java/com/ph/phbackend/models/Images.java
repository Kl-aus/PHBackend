package com.ph.phbackend.models;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "image_name", nullable = false)
    private String imageName;

    @Column(name = "image_path", nullable = false)
    private String path;

    public Images() {
    }


    public Images(String imageName, String path) {
        this.imageName = imageName;
        this.path = path;
    }

    public Long getImageId() {
        return imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
