package com.pearmarket.app.beans.elements;

import java.io.Serializable;
import java.util.Locale;

/**
 * JavaBeans d'un produit
 */
public class Product implements Serializable {
    private int id;
    private Category category;
    private String name;
    private String description;
    private String imageSrc;
    private float price;
    private int quantity;
    private String attributes;


    public Product() {}

    public Product(int id, Category category, String name, String description, String imageSrc, float price, int quantity, String attributes) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.imageSrc = imageSrc;
        this.price = price;
        this.quantity = quantity;
        this.attributes = attributes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public float getPrice() {
        return price;
    }

    /**
     * @return Le prix formaté (xx.xx€)
     */
    public String getFormattedPrice() {
        return String.format(Locale.ENGLISH ,"%.2f", price);
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

}


