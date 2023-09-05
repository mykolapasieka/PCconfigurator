package com.example.javafxdiplomawork.model;

import javafx.scene.image.Image;

public class Motherboard {
    private String id;
    private String chipset;
    private String formFactor;
    private int idFormFactor;
    private String manufacturer;
    private String memoryType;
    private String maxMemoryFrequency;
    private String name;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String socket;

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturerId) {
        this.manufacturer = manufacturerId;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public String getMaxMemoryFrequency() {
        return maxMemoryFrequency;
    }

    public void setMaxMemoryFrequency(String maxMemoryFrequency) {
        this.maxMemoryFrequency = maxMemoryFrequency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
