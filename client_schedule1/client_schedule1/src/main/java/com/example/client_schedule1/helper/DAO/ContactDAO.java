package com.example.client_schedule1.helper.DAO;



import com.example.client_schedule1.model.Contact;

import java.sql.SQLException;
/**This is the contact DAO interface*/
public interface ContactDAO extends com.example.client_schedule1.helper.DAO.DAO<Contact> {
    /**This is implemented insert a contact into the database
     * @param contact the contact being inserted */
    int insert(Contact contact) throws SQLException;
}
