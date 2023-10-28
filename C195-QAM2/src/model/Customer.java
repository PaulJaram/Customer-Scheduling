package model;

import java.sql.Timestamp;

public class Customer {
    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private Timestamp dateCreated;
    private String createdBy;
    private Timestamp lastUpdated;
    private String updatedBy;
    private int divisionID;


    public Customer(){

    }

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


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }
    public int getDivisionID() {
        return divisionID;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phone='" + phone + '\'' +
                ", dateCreated=" + dateCreated +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", updatedBy='" + updatedBy + '\'' +
                ", divisionID=" + divisionID +
                '}';
    }
}
