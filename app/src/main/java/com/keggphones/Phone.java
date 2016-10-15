package com.keggphones;

/**
 * Created by mm on 12/10/2016.
 */
public class Phone {

    private int idPhone;
    private String Brand;
    private String model;
    private String internalMemory;
    private String externalMemory;
    private int pixels;
    private byte flash;
    private String resolution;
    private int price;
    private int priceDolar;
    private int quantity;
    private String imagePhone;
    private int image;

    public Phone(String brand, String model, String internalMemory, String externalMemory,
                 int pixels, byte flash, String resolution, int price, int priceDolar, int image) {
        Brand = brand;
        this.model = model;
        this.internalMemory = internalMemory;
        this.externalMemory = externalMemory;
        this.pixels = pixels;
        this.flash = flash;
        this.resolution = resolution;
        this.price = price;
        this.image = image;
        this.priceDolar = priceDolar;
    }

    public Phone(String model, int price, int priceDolar, int image){
        this.model = model;
        this.price = price;
        this.priceDolar = priceDolar;
        this.image = image;

    }

    /*
    public Phone(int idPhone, int idBrand, String model,
                 String internalMemory, String externalMemory,
                 int pixels, byte flash, String resolution,
                 int price, int quantity, String imagePhone) {
        this.idPhone = idPhone;
        this.idBrand = idBrand;
        this.model = model;
        this.internalMemory = internalMemory;
        this.externalMemory = externalMemory;
        this.pixels = pixels;
        this.flash = flash;
        this.resolution = resolution;
        this.price = price;
        this.quantity = quantity;
        this.imagePhone = imagePhone;
    }*/

    public int getIdPhone() {
        return idPhone;
    }

    public void setIdPhone(int idPhone) {
        this.idPhone = idPhone;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public int getPixels() {
        return pixels;
    }

    public void setPixels(int pixels) {
        this.pixels = pixels;
    }

    public byte getFlash() {
        return flash;
    }

    public void setFlash(byte flash) {
        this.flash = flash;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImagePhone() {
        return imagePhone;
    }

    public void setImagePhone(String imagePhone) {
        this.imagePhone = imagePhone;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPriceDolar() {
        return priceDolar;
    }

    public void setPriceDolar(int priceDolar) {
        this.priceDolar = priceDolar;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }
}
