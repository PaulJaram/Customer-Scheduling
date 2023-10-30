package dao;

import model.Appointment;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{
    @Override
    public User get(int id) throws SQLException {
        Connection conn = JDBC.getConnection();
        User user = null;
        String sql = "SELECT User_ID, User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By " +
                "FROM Users " +
                "WHERE User_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            int oid = rs.getInt("User_ID");
            String name = rs.getString("User_Name");
            String password = rs.getString("Password");
            Timestamp dateCreated = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdated = rs.getTimestamp("Last_Update");
            String updatedBy = rs.getString("Last_Updated_BY");

            user = new User(oid, name, password, dateCreated, createdBy, lastUpdated, updatedBy);
        }
        JDBC.closePreparedStatement(ps);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);

        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        Connection conn = JDBC.getConnection();
        List<User> users = new ArrayList<>();
        String sql = "SELECT User_ID, User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By " +
                "FROM Users";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            int oid = rs.getInt("User_ID");
            String name = rs.getString("User_Name");
            String password = rs.getString("Password");
            Timestamp dateCreated = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdated = rs.getTimestamp("Last_Update");
            String updatedBy = rs.getString("Last_Updated_BY");

           User user = new User(oid, name, password, dateCreated, createdBy, lastUpdated, updatedBy);
           users.add(user);
        }
        JDBC.closeStatement(statement);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);

        return users;
    }

    @Override
    public int insert(User user) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "INSERT INTO Users(User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS );

        ps.setString(1, user.getName());
        ps.setString(2, user.getPassword());
        ps.setTimestamp(3, user.getDateCreated());
        ps.setString(4, user.getCreatedBy());
        ps.setTimestamp(5, user.getLastUpdated());
        ps.setString(6, user.getUpdatedBy());

        int result = ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();

        if(rs.next()){
            int id = rs.getInt(1);
            user.setId(id);
        }

        JDBC.closePreparedStatement(ps);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);

        return result;
    }

    @Override
    public int update(User user) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "UPDATE Users SET User_Name = ?, Password = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ? " +
                "WHERE User_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, user.getName());
        ps.setString(2, user.getPassword());
        ps.setTimestamp(3, user.getDateCreated());
        ps.setString(4, user.getCreatedBy());
        ps.setTimestamp(5, user.getLastUpdated());
        ps.setString(6, user.getUpdatedBy());
        ps.setInt(7, user.getId());

        int result = ps.executeUpdate();

        JDBC.closePreparedStatement(ps);
        JDBC.closeConnection(conn);

        return result;
    }

    @Override
    public int delete(User user) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "DELETE FROM Users WHERE User_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, user.getId());

        int result = ps.executeUpdate();

        JDBC.closePreparedStatement(ps);
        JDBC.closeConnection(conn);

        return result;
    }
}
