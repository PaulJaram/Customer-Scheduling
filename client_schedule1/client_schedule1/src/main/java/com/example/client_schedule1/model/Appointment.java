package com.example.client_schedule1.model;

import java.sql.Timestamp;
/** This class has members and methods for the Appointment Object.*/
public class Appointment {
    /** This is the appointment ID*/
    private int id;
    /** This is the title of the appointment*/
    private String title;
    /** This is the description of the appointment*/
    private String description;
    /** This is the location of the appointment*/
    private String location;
    /** This is the type of appointment*/
    private String type;
    /** This is the start date and time of the appointment*/
    private Timestamp start;
    /** This is the end date and time of the appointment*/
    private Timestamp end;
    /** This is when the appointment was created*/
    private Timestamp dateCreated;
    /** This is the user that created the appointment*/
    private String createdBy;
    /** This is the date and time the appointment was last updated*/
    private Timestamp lastUpdated;
    /** This is the user that updated the appointment*/
    private String updatedBy;
    /** This is the ID of the customer the appointment is for*/
    private int customer;
    /** This is the ID of the user setting up the appointment*/
    private int user;
    /** This is the contact for the appointment*/
    private int contact;

    /** This is the default method constructor. This method allows the Appointment class to be instantiated*/
    public Appointment() {

    }
    /** This is the Appointment constructor that assigns all attributes a value.*/
    public Appointment(int id, String title, String description, String location, String type, Timestamp start, Timestamp end, Timestamp dateCreated, String createdBy, Timestamp lastUpdated, String updatedBy, int customerID, int userID, int contactID) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.updatedBy = updatedBy;
        this.customer = customerID;
        this.user = userID;
        this.contact = contactID;
    }
    /** This is the Method that allows attributes of a particular appointment to be viewed in strings.*/
    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", dateCreated=" + dateCreated +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", updatedBy='" + updatedBy + '\'' +
                ", customer=" + customer +
                ", user=" + user +
                ", contact=" + contact +
                '}';
    }
    /** This sets the ID.
     * @param id The ID*/
    public void setID(int id) {
        this.id = id;
    }
    /** This sets the title
     * @param title the title*/
    public void setTitle(String title) {
        this.title = title;
    }
    /** This sets the description.
     * @param description the description*/
    public void setDescription(String description) {
        this.description = description;
    }
    /** This sets the location.
     * @param location the location*/
    public void setLocation(String location) {
        this.location = location;
    }
    /**This sets the type.
     * @param type The type*/
    public void setType(String type) {
        this.type = type;
    }
    /**THis sets the start.
     * @param start the start*/
    public void setStart(Timestamp start) {
        this.start = start;
    }
    /**This sets the end.
     * @param end the end*/
    public void setEnd(Timestamp end) {
        this.end = end;
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
    /**this sets the date last Updated.
     * @param lastUpdated the last updated*/
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    /**this sets the updated by.
     * @param updatedBy the updated by*/
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    /** this sets the customer ID.
     * @param customer customer ID*/
    public void setCustomer(int customer) {
        this.customer = customer;
    }
    /** This sets the user ID.
     * @param user user ID*/
    public void setUser(int user) {
        this.user = user;
    }
    /** This sets the contact ID.
     * @param contact contact ID*/
    public void setContact(int contact) {
        this.contact = contact;
    }
    /** This gets the appointment ID.
     * @return appointment ID*/
    public int getID() {
        return id;
    }
    /** This gets the title.
     * @return title*/
    public String getTitle() {
        return title;
    }
    /**this gets the description.
     * @return description*/
    public String getDescription() {
        return description;
    }
    /**this gets the location.
     * @return location*/
    public String getLocation() {
        return location;
    }
    /** this gets the type.
     * @return type*/
    public String getType() {
        return type;
    }
    /**this gets the start.
     * @return start*/
    public Timestamp getStart() {
        return start;
    }
    /**this gets the end.
     * @return end*/
    public Timestamp getEnd() {
        return end;
    }
    /**this gets the date created.
     * @return created*/
    public Timestamp getDateCreated() {
        return dateCreated;
    }
    /**this gets the created by.
     * @return created by*/
    public String getCreatedBy() {
        return createdBy;
    }
    /** This gets last updated.
     * @return last updated*/
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }
    /**This gets the updated by.
     * @return updated by*/
    public String getUpdatedBy() {
        return updatedBy;
    }
    /**This gets the customer ID.
     * @return customer ID*/
    public int getCustomer() {
        return customer;
    }
    /**This gets the user ID.
     * @return User ID*/
    public int getUser() {
        return user;
    }
    /** This gets the Contact iD.
     * @return Contact ID*/
    public int getContact() {
        return contact;
    }

}
