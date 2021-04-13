package com.example.shillelagh;

public class model {
    private int image;
    private String title;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public model(int image, String title) {
        this.image = image;
        this.title = title;
    }
}
