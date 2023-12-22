package com.example.client_schedule1.model;

import com.example.client_schedule1.helper.DAO.UserDAO;
import com.example.client_schedule1.helper.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**This is the class for the customer object.*/
public class Customer {
    /**This is the customer ID.*/
    private int id;
    /**this is the customer name.*/
    private String name;
    /**this is the customer address.*/
    private String address;
    /**this is the postal code.*/
    private String postalCode;
    /**this is the phone number.*/
    private String phone;
    /**this is the date the customer was created.*/
    private Timestamp dateCreated;
    /**this is the user who created the customer.*/
    private String createdBy;
    /**This is the last time the customer was updated*/
    private Timestamp lastUpdated;
    /**this is the user who updated the customer.*/
    private String updatedBy;
    /**This is the division id of the customer*/
    private int divisionID;

    /**this is the default constructor of the customer. This allows the class to be instantiated.*/
    public Customer(){

    }
    /**this is the customer constructor. This assigns values to the customer attributes.*/
    public Customer(int id, String name, String address, String postalCode, String phone, Timestamp dateCreated, String createdBy, Timestamp lastUpdated, String updatedBy, int divisionID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.updatedBy = updatedBy;
        this.divisionID = divisionID;
    }

    /**This sets the ID
     * @param id the ID*/
    public void setId(int id) {
        this.id = id;
    }
    /**This sets the name.
     * @param name the name*/
    public void setName(String name) {
        this.name = name;
    }
    /**this sets the address.
     * @param address the address*/
    public void setAddress(String address) {
        this.address = address;
    }
    /**this sets the postal code.
     * @param postalCode the postal code*/
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    /**this sets the phone number.
     * @param phone the phone number*/
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**this sets the date created.
     * @param dateCreated the date created*/
    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
    /**this sets the created by.
     * @param createdBy the created by*/
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /**this sets the last updated.
     * @param lastUpdated the last updated*/
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    /**this sets the updated by.
     * @param updatedBy the updated by*/
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    /**this sets the division id.
     * @param divisionID the division ID*/
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
    /**this gets the customer ID.
     * @return customer ID*/
    public int getId() {
        return id;
    }
    /**This gets the name.
     * @return the name*/
    public String getName() {
        return name;
    }
    /**this gets the address.
     * @return the address*/
    public String getAddress() {
        return address;
    }
    /**this gets the postal code.
     * @return the postal code.*/
    public String getPostalCode() {
        return postalCode;
    }
    /**this gets the phone number.
     * @return the phone number*/
    public String getPhone() {
        return phone;
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
    /**this gets the last updated.
     * @return last updated*/
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }
    /**this gets the updated by.
     * @return the updated by*/
    public String getUpdatedBy() {
        return updatedBy;
    }
    /**This gets the division id.
     * @return the division id*/
    public int getDivisionID() {
        return divisionID;
    }
    /**this makes the customer more readable as a string data type.*/
    @Override
    public String toString() {
        return id + " | " + name;
    }

}
