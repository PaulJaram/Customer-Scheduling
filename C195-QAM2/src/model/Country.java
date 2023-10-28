package model;

import java.sql.Timestamp;

public class Country {
    private int id;
    private String name;
    private Timestamp dateCreated;
    private String createdBy;
    private Timestamp lastUpdated;
    private String updatedBy;

    public Country(){

    }

    public Country(int id, String name, Timestamp dateCreated, String createdBy, Timestamp lastUpdated, String updatedBy) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.updatedBy = updatedBy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
