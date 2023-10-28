package model;

import java.sql.Timestamp;

public class Appointment {
    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private Timestamp start;
    private Timestamp end;
    private Timestamp dateCreated;
    private String createdBy;
    private Timestamp lastUpdated;
    private String updatedBy;
    private int customer;
    private int user;
    private int contact;

    public Appointment(){

    }

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

    public void setID(int id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setStart(Timestamp start){
        this.start = start;
    }
    public void setEnd(Timestamp end){
        this.end = end;
    }
    public void setDateCreated(Timestamp dateCreated){
        this.dateCreated = dateCreated;
    }
    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }
    public void setLastUpdated(Timestamp lastUpdated){
        this.lastUpdated = lastUpdated;
    }
    public void setUpdatedBy(String updatedBy){
        this.updatedBy = updatedBy;
    }
    public void setCustomer(int customer){
        this.customer = customer;
    }
    public void setUser(int user){
        this.user = user;
    }
    public void setContact(int contact){
        this.contact = contact;
    }
    public int getID(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getLocation(){
        return location;
    }
    public String getType(){
        return type;
    }
    public Timestamp getStart(){
        return start;
    }
    public Timestamp getEnd(){
        return end;
    }
    public Timestamp getDateCreated(){
        return dateCreated;
    }
    public String getCreatedBy(){
        return createdBy;
    }
    public Timestamp getLastUpdated(){
        return lastUpdated;
    }
    public String getUpdatedBy(){
        return updatedBy;
    }
    public int getCustomer(){
        return customer;
    }
    public int getUser(){
        return user;
    }
    public int getContact(){
        return contact;
    }
}
