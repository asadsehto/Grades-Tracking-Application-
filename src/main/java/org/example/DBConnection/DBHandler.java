package org.example.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBHandler extends Config {
    public Connection dbConnection;
        public  Connection getConnection() throws SQLException {
            String connectionString = "jdbc:mysql://" + Config.dbHost + ":" + Config.dbPort + "/" + Config.dbName + "?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                dbConnection = DriverManager.getConnection(connectionString, Config.dbUser, Config.dbPass);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            return dbConnection;
        }

    }
