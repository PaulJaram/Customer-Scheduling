package com.example.client_schedule1.helper.DAO;


import com.example.client_schedule1.model.Customer;

import java.sql.SQLException;
/**This is the customer DAO interface*/
public interface CustomerDAO extends com.example.client_schedule1.helper.DAO.DAO<Customer> {
    /**This is implemented to insert a customer in the database.
     * @param customer the customer to be inserted.*/
    int insert(Customer customer) throws SQLException;
}
