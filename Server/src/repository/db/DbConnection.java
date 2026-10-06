package repository.db;

import java.sql.*;

/**
 * 
 * 
 * @author SARA
 */
public class DbConnection {
    private static DbConnection instance;
    private Connection connection;

    private DbConnection() throws ClassNotFoundException {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");

                String url = konfiguracija.Konfiguracija.getInstance().getProperty("url");
                String username = konfiguracija.Konfiguracija.getInstance().getProperty("username");
                String password = konfiguracija.Konfiguracija.getInstance().getProperty("password");
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            System.getLogger(DbConnection.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public static DbConnection getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = konfiguracija.Konfiguracija.getInstance().getProperty("url");
            String username = konfiguracija.Konfiguracija.getInstance().getProperty("username");
            String password = konfiguracija.Konfiguracija.getInstance().getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        }
        return connection;
    }
}
