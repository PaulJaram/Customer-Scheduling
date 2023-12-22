package com.example.client_schedule1.model;

import java.sql.Timestamp;
/**This is the class for the first level division object*/
public class FirstLevelDivision {
    /**this is the division id*/
    private int id;
    /**this is the division name*/
    private String name;
    /**this is the date the division was created in the database*/
    private Timestamp dateCreated;
    /**this is the user who created the division*/
    private String createdBy;
    /**this is the last time the division was updated*/
    private Timestamp lastUpdated;
    /**this is the user who last updated the division*/
    private String updatedBy;
    /**this is the country ID of the divisoion*/
    private int countryID;
    /**this is the default constructor.
     * This allows the constructor to be instantiated*/
    public FirstLevelDivision(){

    }
    /**This is the constructor of the division class.
     * this assigns values to the attributes.*/
    public FirstLevelDivision(int id, String name, Timestamp dateCreated, String createdBy, Timestamp lastUpdated, String updatedBy, int countryID) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.updatedBy = updatedBy;
        this.countryID = countryID;
    }
    /**this sets the ID.
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
     * @return the ID*/
    public int getId() {
        return id;
    }
    /**This returns the name.
     * @return the name*/
    public String getName() {
        return name;
    }
    /**this gets the date created.
     * @return the date created*/
    public Timestamp getDateCreated() {
        return dateCreated;
    }
    /**this gets the created by.
     * @return the created by*/
    public String getCreatedBy() {
        return createdBy;
    }
    /**This gets the last updated.
     * @return the last updated*/
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }
    /**this gets the updated by.
     * @return the updated by*/
    public String getUpdatedBy() {
        return updatedBy;
    }
    /** This gets the country ID.
     * @return the country ID*/
    public int getCountryID() {
        return countryID;
    }
    /**This allows the division string to be more readable as a string data type*/
    @Override
    public String toString() {
        return id +
                " | " + name;
    }
}
