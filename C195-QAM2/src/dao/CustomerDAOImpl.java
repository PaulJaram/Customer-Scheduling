package dao;

import model.Appointment;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO{
    @Override
    public Customer get(int id) throws SQLException {
        Connection conn = JDBC.getConnection();
        Customer customer = null;
        String sql = "Select Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID " +
                "FROM Customers " +
                "WHERE Customer_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            int oid = rs.getInt("Customer_ID");
            String  name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            Timestamp dateCreated = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdated = rs.getTimestamp("Last_Update");
            String updatedBy = rs.getString("Last_Updated_BY");
            int divisionID = rs.getInt("Division_ID");

            customer = new Customer(oid, name, address, postalCode, phone, dateCreated, createdBy, lastUpdated, updatedBy, divisionID);
        }

        JDBC.closePreparedStatement(ps);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);
        return customer;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        Connection conn = JDBC.getConnection();
        List<Customer> customers = new ArrayList<>();
        String sql = "Select Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID " +
                "FROM Customers";
        Statement statement = conn.createStatement();;
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            int oid = rs.getInt("Customer_ID");
            String  name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            Timestamp dateCreated = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdated = rs.getTimestamp("Last_Update");
            String updatedBy = rs.getString("Last_Updated_BY");
            int divisionID = rs.getInt("Division_ID");

            Customer customer = new Customer(oid, name, address, postalCode, phone, dateCreated, createdBy, lastUpdated, updatedBy, divisionID);
            customers.add(customer);
        }

        JDBC.closeStatement(statement);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);
        return customers;
    }

    @Override
    public int insert(Customer customer) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "INSERT INTO Customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, customer.getName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setTimestamp(5, customer.getDateCreated());
        ps.setString(6, customer.getCreatedBy());
        ps.setTimestamp(7, customer.getLastUpdated());
        ps.setString(8, customer.getUpdatedBy());
        ps.setInt(9, customer.getDivisionID());

        int result = ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();

        if(rs.next()){
            int id = rs.getInt(1);
            customer.setId(id);
        }

        JDBC.closePreparedStatement(ps);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);

        return result;
    }

    @Override
    public int update(Customer customer) throws SQLException {
        Connection conn = JDBC.getConnection();

        String sql = "UPDATE Customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? " +
                "WHERE Customer_ID = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
//FIX ME Set parameter index for ID to last index
        ps.setString(1, customer.getName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setTimestamp(5, customer.getDateCreated());
        ps.setString(6, customer.getCreatedBy());
        ps.setTimestamp(7, customer.getLastUpdated());
        ps.setString(8, customer.getUpdatedBy());
        ps.setInt(9, customer.getDivisionID());
        ps.setInt(10, customer.getId());

        int result = ps.executeUpdate();

        JDBC.closePreparedStatement(ps);
        JDBC.closeConnection(conn);

        return result;
    }

    @Override
    public int delete(Customer customer) throws SQLException {
        Connection conn = JDBC.getConnection();

        String sql = "DELETE FROM Customers WHERE Customer_ID = ?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, customer.getId());

        int result = ps.executeUpdate();

        JDBC.closePreparedStatement(ps);
        JDBC.closeConnection(conn);

        return result;
    }
}
