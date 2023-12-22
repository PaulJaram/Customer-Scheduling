package com.example.client_schedule1.helper.DAO;

import java.sql.SQLException;
import java.util.List;
/**This is the DAO interface*/
public interface DAO <T> {
    /**This is implemented to get an object from the database.
     * @param id The ID of the object
     * @return the object*/
    T get(int id) throws SQLException;
    /**This is implemented to get a list of all objects in a table.
     * @return list of a particular object*/
    List<T> getAll() throws SQLException;
    /**This is implemented tp insert an object.
     * @param t the object to be inserted*/
    int insert(T t) throws SQLException;
    /**This is implemented to update an object in a database.
     * @param t the object to be updated*/
    int update(T t) throws SQLException;
    /**This is implemented to delete an object.
     * @param t The object to be deleted*/
    int delete(T t) throws SQLException;


}
