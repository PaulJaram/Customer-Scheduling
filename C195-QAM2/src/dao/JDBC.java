package dao;
import java.sql.*;

public class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static final String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface

    public static Connection openConnection()
    {
        try {
            Class.forName(driver);// Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
        return connection;
    }

    public static Connection getConnection() throws SQLException{
       try{
           connection = DriverManager.getConnection(jdbcUrl, userName, password);
       }
       catch(Exception e){
           System.out.println("Error:" + e.getMessage());
       }
        return connection;
    }
    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException{
        preparedStatement.close();
    }
    public static void closeStatement(Statement statement) throws SQLException{
        statement.close();
    }
    public static void closeResultSet(ResultSet resultSet) throws SQLException{
        resultSet.close();
    }
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
    public static void closeConnection(Connection conn) throws SQLException{
        conn.close();
    }
}
