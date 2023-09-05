package com.example.javafxdiplomawork.model;

import javafx.scene.image.Image;

public class GPU {
    private String id;
    private String manufacturer;
    private String series;
    private String watts;
    private String name;
    private String memory_size;
    private String price;
    private Image image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getWatts() {
        return watts;
    }

    public void setWatts(String watts) {
        this.watts = watts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemory_size() {
        return memory_size;
    }

    public void setMemory_size(String memory_size) {
        this.memory_size = memory_size;
    }

}
