package com.example.client_schedule1.model;

import java.sql.Timestamp;
/**This is the class for the User Object*/
public class User {
    /**this is the user ID.*/
    private int id;
    /**This is the username.*/
    private String name;
    /**this is the password of the user.*/
    private String password;
    /**this is the date the user was created.*/
    private Timestamp dateCreated;
    /**This is the user who created the user.*/
    private String createdBy;
    /**this is the last time the user was updated.*/
    private Timestamp lastUpdated;
    /**this is the user who last updated the user.*/
    private String updatedBy;
    /**The user default constructor.
     * this allows the user to be instantiated.*/
    public User(){

    }
    /**The user constructor.
     * this assigns attributes values.*/
    public User(int id, String name, String password, Timestamp dateCreated, String createdBy, Timestamp lastUpdated, String updatedBy) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.updatedBy = updatedBy;
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
    /**this gets the name.
     * @return the name*/
    public String getName() {
        return name;
    }
    /**this gets the password.
     * @return the password*/
    public String getPassword() {
        return password;
    }
    /**this gets the date created.
     * @return the date created.*/
    public Timestamp getDateCreated() {
        return dateCreated;
    }
    /**this gets the created by.
     * @return the created by.*/
    public String getCreatedBy() {
        return createdBy;
    }
    /**this gets the last updated.
     * @return the last updated*/
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }
    /**this gets the updated by.
     * @return the updated by*/
    public String getUpdatedBy() {
        return updatedBy;
    }
    /**this allows the user to be more readable as a string data type.*/
    @Override
    public String toString() {
        return id + " | " + name;
    }
}
