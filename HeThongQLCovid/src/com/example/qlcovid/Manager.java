/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlcovid;
import java.sql.*;
/**
 *
 * @author nhonnhon
 */
public class Manager {
    ManagerUI mgUI;
    Manager(){
        //mgUI = new ManagerUI(1);
        //mgUI.setLocationRelativeTo(null);
        //mgUI.setVisible(true);
        //mgUI.setSize(1600, 900);
        testDB();
        
    }

    private void testDB() throws ClassNotFoundException {
        String dbURL = "jdbc:sqlserver://localhost;databaseName=QLCovid;user=sa;password=123456";
        Connection conn = DriverManager.getConnection(dbURL);
        if (conn != null) {
            System.out.println("Connected");
        }
        try {
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery("select * from dbo.ward");
            // show data
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) );
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
