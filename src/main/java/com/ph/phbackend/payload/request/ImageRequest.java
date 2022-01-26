package com.ph.phbackend.payload.request;

import org.springframework.web.multipart.MultipartFile;

public class ImageRequest {
    MultipartFile file;
    String title;

    public ImageRequest() {
    }

    public ImageRequest(MultipartFile file, String title) {
        this.file = file;
        this.title = title;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
