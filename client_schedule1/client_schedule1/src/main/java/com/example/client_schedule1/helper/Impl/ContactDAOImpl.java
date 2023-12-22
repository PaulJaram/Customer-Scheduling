package com.example.client_schedule1.helper.Impl;

import com.example.client_schedule1.model.Contact;
import com.example.client_schedule1.helper.DAO.ContactDAO;
import com.example.client_schedule1.helper.JDBC;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**This is the contact dao implementation class*/
public class ContactDAOImpl implements ContactDAO {
    /**This gets a contact from the database.
     * @param id the ID of the Contact that is being grabbed from the database
     * @return a Contact from the database*/
    @Override
    public Contact get(int id) throws SQLException {
        Connection conn = JDBC.getConnection();
        Contact contact = null;
        String sql = "SELECT Contact_ID, Contact_Name, Email " +
                "FROM Contacts " +
                "WHERE Contact_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            int oid = rs.getInt("Contact_ID");
            String name = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            contact = new Contact();
            contact.setId(oid);
            contact.setName(name);
            contact.setEmail(email);
        }
        JDBC.closePreparedStatement(ps);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);

        return contact;
    }
    /**This gets a list of all contacts from a table.
     * @return all contacts from a table*/
    @Override
    public List<Contact> getAll() throws SQLException {
        Connection conn = JDBC.getConnection();
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT Contact_ID, Contact_Name, Email " +
                "FROM Contacts";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            int oid = rs.getInt("Contact_ID");
            String name = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            Contact contact = new Contact(oid, name, email);
            contacts.add(contact);
        }
        JDBC.closeStatement(statement);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);

        return contacts;
    }
    /**This inserts a Contact in the database.
     * @param contact the contact to be inserted*/
    @Override
    public int insert(Contact contact) throws SQLException {
        Connection conn  = JDBC.getConnection();
        String sql = "INSERT INTO Contacts (Contact_Name, Email) VALUES(?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, contact.getName());
        ps.setString(2, contact.getEmail());

        int result = ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();

        if(rs.next()){
            int id = rs.getInt(1);
            contact.setId(id);
        }

        JDBC.closePreparedStatement(ps);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);

        return result;
    }
    /**This updates the contact in the database.
     * @param contact the contact to be updated*/
    @Override
    public int update(Contact contact) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "UPDATE Contacts SET Contact_Name = ?, Email = ? " +
                "WHERE Contact_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, contact.getName());
        ps.setString(2, contact.getEmail());
        ps.setInt(3, contact.getId());

        int result = ps.executeUpdate();

        JDBC.closePreparedStatement(ps);
        JDBC.closeConnection(conn);

        return result;
    }
    /**This deletes a Contact from the database.
     * @param contact the contact to be deleted*/
    @Override
    public int delete(Contact contact) throws SQLException{
        Connection conn = JDBC.getConnection();
        String sql = "DELETE FROM Contacts WHERE Contact_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, contact.getId());

        int result = ps.executeUpdate();

        JDBC.closePreparedStatement(ps);
        JDBC.closeConnection(conn);

        return result;
    }
}
