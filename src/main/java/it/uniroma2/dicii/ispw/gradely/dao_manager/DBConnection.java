package it.uniroma2.dicii.ispw.gradely.dao_manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private DBConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream(".properties"));
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public static synchronized DBConnection getInstance() throws IOException, SQLException {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
