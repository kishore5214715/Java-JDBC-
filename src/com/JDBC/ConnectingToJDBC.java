package com.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConnectingToJDBC {
    private static final String url = "jdbc:postgresql://localhost:5432/demo";
    private final static String username = "postgres";
    private final static String password = "kishore";
    static PreparedStatement connect(String query){
        try {
            //connecting to database using the driver manager
            Connection con = DriverManager.getConnection(url, username, password);
            //use the prepare statement to execute the placeholder Values
            PreparedStatement stm = con.prepareStatement(query);
            //return the PreparedStatement type data
            return stm;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
