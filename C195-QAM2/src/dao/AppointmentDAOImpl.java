package dao;

import model.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO{
    @Override
    public Appointment get(int id) throws SQLException {
        Connection conn = JDBC.getConnection();
        Appointment appointment = null;
        String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID " +
                "FROM Appointments " +
                "WHERE Appointment_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            int oid = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp dateCreated = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdated = rs.getTimestamp("Last_Update");
            String updatedBy = rs.getString("Last_Updated_BY");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            appointment = new Appointment(oid, title, description, location, type, start, end, dateCreated, createdBy, lastUpdated, updatedBy, customerID, userID, contactID);
        }
        JDBC.closePreparedStatement(ps);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);

        return appointment;
    }

    @Override
    public List<Appointment> getAll() throws SQLException {
        Connection conn = JDBC.getConnection();
        List<Appointment> appointments= new ArrayList<>();
        String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID " +
                "FROM APPOINTMENTS";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            int oid = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");
            Timestamp dateCreated = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdated = rs.getTimestamp("Last_Update");
            String updatedBy = rs.getString("Last_Updated_BY");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            Appointment appointment = new Appointment(oid, title, description, location, type, start, end, dateCreated, createdBy, lastUpdated, updatedBy, customerID, userID, contactID);
            appointments.add(appointment);
        }
        JDBC.closeStatement(statement);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);

        return appointments;
    }

    @Override
    public int insert(Appointment appointment) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "INSERT INTO Appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_by, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getType());
        ps.setTimestamp(5, appointment.getStart());
        ps.setTimestamp(6, appointment.getEnd());
        ps.setTimestamp(7, appointment.getDateCreated());
        ps.setString(8, appointment.getCreatedBy());
        ps.setTimestamp(9, appointment.getLastUpdated());
        ps.setString(10, appointment.getUpdatedBy());
        ps.setInt(11, appointment.getCustomer());
        ps.setInt(12, appointment.getUser());
        ps.setInt(13, appointment.getContact());

        int result = ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();

        if(rs.next()){
            int id = rs.getInt(1);
            appointment.setID(id);
        }

        JDBC.closePreparedStatement(ps);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);

        return result;
    }

    @Override
    public int update(Appointment appointment) throws SQLException {
        Connection conn= JDBC.getConnection();
        String sql = "UPDATE Appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? " +
                "WHERE Appointment_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getType());
        ps.setTimestamp(5, appointment.getStart());
        ps.setTimestamp(6, appointment.getEnd());
        ps.setTimestamp(7, appointment.getDateCreated());
        ps.setString(8, appointment.getCreatedBy());
        ps.setTimestamp(9, appointment.getLastUpdated());
        ps.setString(10, appointment.getUpdatedBy());
        ps.setInt(11, appointment.getCustomer());
        ps.setInt(12, appointment.getUser());
        ps.setInt(13, appointment.getContact());
        ps.setInt(14, appointment.getID());

        int result = ps.executeUpdate();

        JDBC.closePreparedStatement(ps);
        JDBC.closeConnection(conn);

        return result;
    }

    @Override
    public int delete(Appointment appointment) throws SQLException{
        Connection conn = JDBC.getConnection();
        String sql = "DELETE FROM Appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, appointment.getID());

        int result = ps.executeUpdate();

        JDBC.closePreparedStatement(ps);
        JDBC.closeConnection(conn);

        return result;
    }
}
