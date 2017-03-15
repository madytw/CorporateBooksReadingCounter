package com.epam.library.connector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Alexandra_Lavrenteva on 3/15/2017.
 */

public class DBConnector {
    private static DBConnector instance;
    private static final Logger LOG = LogManager.getLogger();
    private DBConnector(){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            LOG.fatal("Cannot register driver." + e);
            throw new RuntimeException(e);
        }
    }
    public static void registerDB(){
        instance=new DBConnector();
    }
}
