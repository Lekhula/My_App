package com.pabi.my_app;

/**
 * Created by Admin on 1/19/2018.
 */

public class Places {

    public String name, image, description;

    public Places(){}

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

    public Places(String name, String image, String description){
        this.name = name;
        this.image = image;
        this.description = description;
    }
}
