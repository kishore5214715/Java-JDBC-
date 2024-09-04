package com.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class BatchExecution {
    public static void main(String[] args) {
        String sql = "insert into jet(id,name,age) values(?,?,?)";
        try {
            //to connect with the data base
            Scanner sc = new Scanner(System.in);
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo", "postgres", "kishore");
            //to modify with the db and use an preparedStatement
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            for(int i=0;i<2;i++) {
                //get the input form hte user
                preparedStatement.setInt(1,sc.nextInt());
                preparedStatement.setString(2,sc.next());
                preparedStatement.setInt(3,sc.nextInt());

                //add to the batches
                preparedStatement.addBatch();
            }
            //execute the batches
            preparedStatement.executeBatch();
            System.out.println("Done");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
