package com.example.javafxdiplomawork.model;

import javafx.scene.image.Image;


public class PCCase {
    private String id;
    private String manufacturer;
    private String name;
    private String formFactor;
    private int idFormFactor;
    private String price;
    private Image image;

    public int getIdFormFactor() {
        return idFormFactor;
    }

    public void setIdFormFactor(int idFormFactor) {
        this.idFormFactor = idFormFactor;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String form_factor) {
        this.formFactor = form_factor;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
