package com.example.javafxdiplomawork.model;

import javafx.scene.image.Image;

public class CPU {
    private String name;
    private String threads;

    private String id;
    private String TDP;
    private String price;
    private String socket;
    private String compatibilityMemory;
    private String frequency;
    private Image image;

    private int manufacturerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTDP() {
        return TDP;
    }

    public void setTDP(String TDP) {
        this.TDP = TDP;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThreads() {
        return threads;
    }

    public void setThreads(String threads) {
        this.threads = threads;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getCompatibilityMemory() {
        return compatibilityMemory;
    }

    public void setCompatibilityMemory(String compatibilityMemory) {
        this.compatibilityMemory = compatibilityMemory;
    }

}
