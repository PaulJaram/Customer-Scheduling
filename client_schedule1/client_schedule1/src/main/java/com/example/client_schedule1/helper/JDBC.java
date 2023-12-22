package com.example.client_schedule1.helper;
import java.sql.*;
/**This is the Java Database Connectivity class*/
public class JDBC {
    /**The protocol*/
    private static final String protocol = "jdbc";
    /**The vendor*/
    private static final String vendor = ":mysql:";
    /**The location*/
    private static final String location = "//localhost/";
    /**The database name*/
    private static final String databaseName = "client_schedule";
    /**The url*/
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    /**the driver*/
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    /**the username*/
    private static final String userName = "sqlUser"; // Username
    /**the password*/
    private static final String password = "Passw0rd!"; // Password
    /**the connection instantiation*/
    public static Connection connection;  // Connection Interface
    /**This opens the connection to the database*/
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
    /**This grabs an open connection*/
    public static Connection getConnection() throws SQLException{
        try{
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
        }
        catch(Exception e){
            System.out.println("Error:" + e.getMessage());
        }
        return connection;
    }
    /**This closes a prepared statement*/
    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException{
        preparedStatement.close();
    }
    /**This closes a statement*/
    public static void closeStatement(Statement statement) throws SQLException{
        statement.close();
    }
    /**This closes a result set*/
    public static void closeResultSet(ResultSet resultSet) throws SQLException{
        resultSet.close();
    }
    /**this closes a connection*/
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
    /**This closes a connection*/
    public static void closeConnection(Connection conn) throws SQLException{
        conn.close();
    }
}
