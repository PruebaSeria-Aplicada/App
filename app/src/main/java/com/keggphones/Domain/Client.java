package com.keggphones.Domain;

/**
 * Created by mm on 17/10/2016.
 */
public class Client {

    private String name;
    private String lastName_1;
    private String lastName_2;
    private String nameUser;
    private String password;
    private String email;
    private String numberCard;
    private String address;
    private String postalCode;
    private String svcCard;


    public Client(String name, String lastName_1, String lastName_2, String nameUser, String password,
                  String email, String numberCard, String address, String postalCode, String svcCard) {
        this.name = name;
        this.lastName_1 = lastName_1;
        this.lastName_2 = lastName_2;
        this.nameUser = nameUser;
        this.password = password;
        this.email = email;
        this.numberCard = numberCard;
        this.address = address;
        this.postalCode = postalCode;
        this.svcCard = svcCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName_1() {
        return lastName_1;
    }

    public void setLastName_1(String lastName_1) {
        this.lastName_1 = lastName_1;
    }

    public String getLastName_2() {
        return lastName_2;
    }

    public void setLastName_2(String lastName_2) {
        this.lastName_2 = lastName_2;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSvcCard() {
        return svcCard;
    }

    public void setSvcCard(String svcCard) {
        this.svcCard = svcCard;
    }
}
