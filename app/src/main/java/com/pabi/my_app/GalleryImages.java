package com.pabi.my_app;

/**
 * Created by Admin on 3/1/2018.
 */

public class GalleryImages {

    GalleryImages(){}

    public String name, image, description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GalleryImages(String name, String image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }
}
