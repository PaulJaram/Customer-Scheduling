package com.example.client_schedule1.model;

import java.sql.Timestamp;
/**This is the class for the country object*/
public class Country {
    /**this is the country ID.*/
    private int id;
    /**This is the country name.*/
    private String name;
    /**This is the date the country was created in the database*/
    private Timestamp dateCreated;
    /**This is the user that created the database*/
    private String createdBy;
    /**this is the last time the country was updated*/
    private Timestamp lastUpdated;
    /**this is the user who updated the country*/
    private String updatedBy;
    /**this is the default constructor of the country class.
     * this allows country to be instantiated.*/
    public Country(){

    }
    /**This is the constructor of the country class.
     * This assigns values to the attributes.*/
    public Country(int id, String name, Timestamp dateCreated, String createdBy, Timestamp lastUpdated, String updatedBy) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.updatedBy = updatedBy;
    }
    /**This sets the ID.
     * @param id the ID*/
    public void setId(int id) {
        this.id = id;
    }
    /**this sets the name.
     * @param name the name*/
    public void setName(String name) {
        this.name = name;
    }
    /**this gets the ID.
     * @return ID*/
    public int getId() {
        return id;
    }
    /**This gets the Name.
     * @return  the name*/
    public String getName() {
        return name;
    }
    /**This gets the date created.
     * @return the date created*/
    public Timestamp getDateCreated() {
        return dateCreated;
    }
    /**This gets the created by.
     * @return the created by*/
    public String getCreatedBy() {
        return createdBy;
    }
    /**This gets the last updated.
     * @return the last updated.*/
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }
    /**This gets the updated by.
     * @return the updated by*/
    public String getUpdatedBy() {
        return updatedBy;
    }
    /**This method allows the contact to be readable as a string data type*/
    @Override
    public String toString() {
        return  id +
                " | " + name;
    }
}
