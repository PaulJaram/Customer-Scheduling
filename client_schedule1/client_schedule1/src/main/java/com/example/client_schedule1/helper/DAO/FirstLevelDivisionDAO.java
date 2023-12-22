package com.example.client_schedule1.helper.DAO;

import com.example.client_schedule1.model.FirstLevelDivision;

import java.sql.SQLException;
/**This is the first level division DAo interface*/
public interface FirstLevelDivisionDAO extends DAO<FirstLevelDivision> {
    /**This is implemented to insert a division in the database.
     * @param division the division to be inserted.*/
    int insert(FirstLevelDivision division) throws SQLException;
}
