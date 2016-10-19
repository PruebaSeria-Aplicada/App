package com.keggphones.Domain;

/**
 * Created by mm on 18/10/2016.
 */
public class Brand {

    private int idBrand;
    private String name;


    public Brand(int idBrand, String name) {
        this.idBrand = idBrand;
        this.name = name;
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
