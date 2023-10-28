package model;

import java.sql.Timestamp;

public class FirstLevelDivision {
    private int id;
    private String name;
    private Timestamp dateCreated;
    private String createdBy;
    private Timestamp lastUpdated;
    private String updatedBy;
    private int countryID;

    public FirstLevelDivision(){

    }

    public FirstLevelDivision(int id, String name, Timestamp dateCreated, String createdBy, Timestamp lastUpdated, String updatedBy, int countryID) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.updatedBy = updatedBy;
        this.countryID = countryID;
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

    public void setCountry(int countryID) {
        this.countryID = countryID;
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

    public int getCountryID() {
        return countryID;
    }

    @Override
    public String toString() {
        return "FirstLevelDivision{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", updatedBy='" + updatedBy + '\'' +
                ", countryID=" + countryID +
                '}';
    }
}
