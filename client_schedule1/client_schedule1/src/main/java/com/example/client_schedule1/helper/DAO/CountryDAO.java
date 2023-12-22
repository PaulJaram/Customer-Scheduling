package com.example.client_schedule1.helper.DAO;


import com.example.client_schedule1.model.Country;

import java.sql.SQLException;
/**This is the country DAO interface*/
public interface CountryDAO extends com.example.client_schedule1.helper.DAO.DAO<Country> {
    /**This is implemented to insert a country in the database.
     * @param country the country being inserted.*/
    int insert(Country country) throws SQLException;
}
