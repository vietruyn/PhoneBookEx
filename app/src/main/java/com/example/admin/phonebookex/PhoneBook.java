package com.example.admin.phonebookex;

/**
 * Created by jce on 8/10/15.
 */
public class PhoneBook {

    //-> Atributos (Comunes)

    private String name, phoneNumber, email;


    //-> Constructor
    public PhoneBook(){

    }
    public PhoneBook(String name, String email, String phoneNumber) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    //-> Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
