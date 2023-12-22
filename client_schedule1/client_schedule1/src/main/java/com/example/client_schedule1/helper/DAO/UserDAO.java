package com.example.client_schedule1.helper.DAO;

import com.example.client_schedule1.model.User;

import java.sql.SQLException;
/**This is the User DAO interface*/
public interface UserDAO extends DAO<User> {
    /**This is implemented to insert a user in the database.
     * @param user the user to be inserted.*/
    int insert(User user) throws SQLException;
}
