package com.JDBC;

import java.sql.*;
import java.util.Scanner;

public class ACIDProperty {
    private static String SELECT = "select * from userdetails";
    private static String S_AMOUNT = "update userdetails set balance = balance - ? where accountno = ?";
    private static String R_AMOUNT = "update userdetails set balance = balance + ? where accountno = ?";

    private static Connection con;

    // Static block execute before the main method
    static {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo","postgres","kishore");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    // to display the db data
    public static void disp(){
        try {
            Statement show = con.createStatement();
            ResultSet re = show.executeQuery(SELECT);
            while(re.next()){
                System.out.println(re.getInt(1)+" "+
                        re.getString(2)+" "+
                        re.getString(3)+" "+
                        re.getString(4));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    static void sendmoney(int sendMoney, int senderAccount) throws Exception{
        PreparedStatement se = con.prepareStatement(S_AMOUNT);
        se.setInt(2, senderAccount);
        se.setInt(1, sendMoney);
        se.executeUpdate();
    }

    static void receivemoney(int receiveraccount,int sendMoney) throws Exception{
        PreparedStatement stmt = con.prepareStatement(R_AMOUNT);
        stmt.setInt(2,receiveraccount);
        stmt.setInt(1,sendMoney);
        stmt.executeUpdate();
    }

    public static void main(String[] args) throws Exception {


        disp();


        Scanner sc2 = new Scanner(System.in);
        System.out.println("Enter sender account");
        int senderAccount=sc2.nextInt();
        System.out.println("Enter the money you send");
        int sendMoney = sc2.nextInt();
        System.out.println("Enter receiver account");
        int receiveraccount = sc2.nextInt();


        con.setAutoCommit(false);//disable the auto commit

        //reduce money form the sender account
        sendmoney(sendMoney,senderAccount);

        //add the money to the receiver account
        receivemoney(receiveraccount,sendMoney);

        con.commit();//finally commit the changes


        disp();
    }

}
