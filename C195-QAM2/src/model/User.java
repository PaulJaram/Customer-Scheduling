package model;

import java.sql.Timestamp;

public class User {
    private int id;
    private String name;
    private String password;
    private Timestamp dateCreated;
    private String createdBy;
    private Timestamp lastUpdated;
    private String updatedBy;

    public User(){

    }

    public User(int id, String name, String password, Timestamp dateCreated, String createdBy, Timestamp lastUpdated, String updatedBy) {
        this.id = id;
        this.name = name;
        this.password = password;
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

    public void setPassword(String password) {
        this.password = password;
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

    public String getPassword() {
        return password;
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
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", dateCreated=" + dateCreated +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
