package dao;

import model.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAOImpl implements ContactDAO{
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

            contact = new Contact(oid, name, email);
        }
        JDBC.closePreparedStatement(ps);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);

        return contact;
    }

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
