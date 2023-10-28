package dao;

import model.FirstLevelDivision;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FirstLevelDivisionDAOImpl implements FirstLevelDivisionDAO{
    @Override
    public FirstLevelDivision get(int id) throws SQLException {
        Connection conn = JDBC.getConnection();
        FirstLevelDivision division = null;
        String sql = "SELECT Division_ID, Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID " +
                "FROM First_Level_Divisions " +
                "WHERE Division_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            int oid = rs.getInt("Division_ID");
            String name = rs.getString("Division");
            Timestamp dateCreated = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdated = rs.getTimestamp("Last_Update");
            String updatedBy = rs.getString("Last_Updated_By");
            int countryID = rs.getInt("Country_ID");

            division = new FirstLevelDivision(oid, name, dateCreated, createdBy, lastUpdated, updatedBy,countryID);
        }
        JDBC.closePreparedStatement(ps);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);

        return division;
    }

    @Override
    public List<FirstLevelDivision> getAll() throws SQLException {
        Connection conn = JDBC.getConnection();
        List<FirstLevelDivision> divisions = new ArrayList<>();
        String sql = "SELECT Division_ID, Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID " +
                "FROM First_Level_Divisions";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            int oid = rs.getInt("Division_ID");
            String name = rs.getString("Division");
            Timestamp dateCreated = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdated = rs.getTimestamp("Last_Update");
            String updatedBy = rs.getString("Last_Updated_By");
            int countryID = rs.getInt("Country_ID");

            FirstLevelDivision division = new FirstLevelDivision(oid, name, dateCreated, createdBy, lastUpdated, updatedBy,countryID);
            divisions.add(division);
        }
        JDBC.closeStatement(statement);
        JDBC.closeResultSet(rs);
        JDBC.closeConnection(conn);

        return divisions;
    }

    @Override
    public int insert(FirstLevelDivision division) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "INSERT INTO First_Level_Divisions (Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, division.getName());
        ps.setTimestamp(2, division.getDateCreated());
        ps.setString(3, division.getCreatedBy());
        ps.setTimestamp(4, division.getLastUpdated());
        ps.setString(5, division.getUpdatedBy());
        ps.setInt(6, division.getCountryID());

        int result = ps.executeUpdate();

        JDBC.closePreparedStatement(ps);
        JDBC.closeConnection(conn);

        return result;
    }

    @Override
    public int update(FirstLevelDivision division) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "UPDATE First_Level_Divisions SET Division = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Update_By = ?, Country_ID = ? " +
                "WHERE Division_ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, division.getName());
        ps.setTimestamp(2, division.getDateCreated());
        ps.setString(3, division.getCreatedBy());
        ps.setTimestamp(4, division.getLastUpdated() );
        ps.setString(5, division.getUpdatedBy());
        ps.setInt(6, division.getCountryID());
        ps.setInt(7, division.getId());

        int result = ps.executeUpdate();

        JDBC.closePreparedStatement(ps);
        JDBC.closeConnection(conn);

        return result;
    }

    @Override
    public int delete(FirstLevelDivision division) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "DELETE FROM First_Level_Divisions WHERE Division_ID = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, division.getId());

        int result = ps.executeUpdate();

        JDBC.closePreparedStatement(ps);
        JDBC.closeConnection(conn);

        return result;
    }
}
