package com.JDBC;

import java.sql.*;

public class MetaData {
    public static void main(String[] args) {
        try{
            //to connect with the data base
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo","postgres","kishore");
            //to create with the db and create an createStatement
            Statement t = con.createStatement();
            //to execute the query and save it in the resultset
            ResultSet s = t.executeQuery("select * from jet");
            //to get the result set meta data
            ResultSetMetaData meta = s.getMetaData();
            //to get the meta data column and it start from the index 1
            int count = meta.getColumnCount();


            for(int i=1;i<count;i++){
                System.out.println(meta.getColumnName(i) /*This is to get the column name*/ +"  "+meta.getColumnTypeName(i)/*This is to get the column type*/);
             }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
