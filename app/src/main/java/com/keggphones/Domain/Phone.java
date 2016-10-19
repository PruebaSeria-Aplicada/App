package com.keggphones.Domain;

public class Phone {

    private String idPhone;
    private String model;
    private String brand;
    private String os;
    private String networkMode;
    private String internalMemory;
    private String externalMemory;
    private String pixels;
    private String flash;
    private String resolution;
    private String price;
    private String priceDolar;
    private String quantity;
    private String image;


    public Phone(String idPhone, String model, String brand, String os, String networkMode,
                 String internalMemory, String externalMemory, String pixels, String flash,
                 String resolution, String price, String quantity, String image) {
        this.setIdPhone(idPhone);
        this.setModel(model);
        this.setBrand(brand);
        this.setOs(os);
        this.setNetworkMode(networkMode);
        this.setInternalMemory(internalMemory);
        this.setExternalMemory(externalMemory);
        this.setPixels(pixels);
        this.setFlash(flash);
        this.setResolution(resolution);
        this.setPrice(price);
        this.quantity = quantity;
        this.image = image;
    }

    public String getIdPhone() {
        return idPhone;
    }

    public void setIdPhone(String idPhone) {
        this.idPhone = idPhone;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getNetworkMode() {
        return networkMode;
    }

    public void setNetworkMode(String networkMode) {
        this.networkMode = networkMode;
    }

    public String getInternalMemory() {
        return internalMemory;
    }

    public void setInternalMemory(String internalMemory) {
        this.internalMemory = internalMemory;
    }

    public String getExternalMemory() {
        return externalMemory;
    }

    public void setExternalMemory(String externalMemory) {
        this.externalMemory = externalMemory;
    }

    public String getPixels() {
        return pixels;
    }

    public void setPixels(String pixels) {
        this.pixels = pixels;
    }

    public String getFlash() {
        return flash;
    }

    public void setFlash(String flash) {
        this.flash = flash;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceDolar() {
        return priceDolar;
    }

    public void setPriceDolar(String priceDolar) {
        this.priceDolar = priceDolar;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}



