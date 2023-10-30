package dao;

import model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAOImpl implements CountryDAO{
    @Override
    public Country get(int id) throws SQLException {
        Connection conn = JDBC.getConnection();
        Country country = null;
        String sql = "SELECT Country_ID, Country, Create_Date, Created_By, Last_Update, Last_Updated_By " +
                "FROM Countries " +
                "WHERE Country_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            int oid = rs.getInt("Country_ID");
            String name = rs.getString("Country");
            Timestamp dateCreated = rs.getTimestamp("Create_Date");
            String createdBy= rs.getString("Created_By");
            Timestamp lastUpdated = rs.getTimestamp("Last_Update");
            String updatedBy = rs.getString("Last_Updated_By");

            country = new Country(oid, name, dateCreated, createdBy, lastUpdated, updatedBy);
        }
        JDBC.closePreparedStatement(ps);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);

        return country;
    }

    @Override
    public List<Country> getAll() throws SQLException {
        Connection conn = JDBC.getConnection();
        List<Country> countries = new ArrayList<>();
        String sql = "SELECT Country_ID, Country, Create_Date, Created_By, Last_Update, Last_Updated_By " +
                "FROM Countries";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            int oid = rs.getInt("Country_ID");
            String name = rs.getString("Country");
            Timestamp dateCreated = rs.getTimestamp("Create_Date");
            String createdBy= rs.getString("Created_By");
            Timestamp lastUpdated = rs.getTimestamp("Last_Update");
            String updatedBy = rs.getString("Last_Updated_By");

           Country country = new Country(oid, name, dateCreated, createdBy, lastUpdated, updatedBy);
           countries.add(country);
        }
        JDBC.closeStatement(statement);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);

        return countries;
    }

    @Override
    public int insert(Country country) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "INSERT INTO Countries (Country, Create_Date, Created_By, Last_Update, Last_Updated_By)VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, country.getName());
        ps.setTimestamp(2, country.getDateCreated());
        ps.setString(3, country.getCreatedBy());
        ps.setTimestamp(4, country.getLastUpdated());
        ps.setString(5, country.getUpdatedBy());

        int result = ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            int id = rs.getInt(1);
            country.setId(id);
        }

        JDBC.closePreparedStatement(ps);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);

        return result;
    }

    @Override
    public int update(Country country) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "UPDATE Countries SET Country = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ? " +
                "WHERE Country_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, country.getName());
        ps.setTimestamp(2, country.getDateCreated());
        ps.setString(3, country.getCreatedBy());
        ps.setTimestamp(4, country.getLastUpdated());
        ps.setString(5, country.getUpdatedBy());
        ps.setInt(6, country.getId());

        int result = ps.executeUpdate();

        JDBC.closePreparedStatement(ps);
        JDBC.closeConnection(conn);

        return result;
    }

    @Override
    public int delete(Country country) throws SQLException{
        Connection conn = JDBC.getConnection();
        String sql = "DELETE FROM Countries WHERE Country_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, country.getId());

        int result = ps.executeUpdate();

        JDBC.closePreparedStatement(ps);
        JDBC.closeConnection(conn);

        return result;

    }
}
