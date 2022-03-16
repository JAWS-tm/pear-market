package com.pearmarket.app.beans.elements;

import java.io.Serializable;

/**
 * JavaBeans Category
 */
public class Category implements Serializable {
    private int id;
    private String name;
    private String description;
    private String imageSrc;

    public Category() {}

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(int id, String name, String description, String imageSrc) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageSrc = imageSrc;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
}
