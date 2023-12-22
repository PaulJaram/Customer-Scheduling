package com.example.client_schedule1.model;
/** This is the class for the contact object.*/
public class Contact {
    /** This is the contact ID*/
    private int id;
    /** This is the contact name*/
    private String name;
    /** This is the contact email*/
    private String email;

    /** THis is the default constructor.
     * Allows class to be instantiated.*/
    public Contact(){

    }
    /** This is the contact constructor.
     * Assigns values to attributes.*/
    public Contact(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    /** This sets the ID.
     * @param id the ID*/
    public void setId(int id) {
        this.id = id;
    }
    /**this sets the name.
     * @param name the name*/
    public void setName(String name) {
        this.name = name;
    }
    /**This sets the email.
     * @param email the email*/
    public void setEmail(String email) {
        this.email = email;
    }
    /**This gets the ID.
     * @return the ID*/
    public int getId() {
        return id;
    }
    /**This gets the name.
     * @return the name*/
    public String getName() {
        return name;
    }
    /**This gets the email.
     * @return the email*/
    public String getEmail() {
        return email;
    }
    /**This allows the contact to be easily readable as a string data type*/
    @Override
    public String toString() {
        return   id +
                " | " + name  +
                " | " + email;
    }
}
