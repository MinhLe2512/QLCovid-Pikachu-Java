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
public class ManagerDB {
    ManagerUI mgUI;
    String dbURL;
    Connection conn;
    ManagerDB(){
        dbURL = "jdbc:sqlserver://localhost;databaseName=QLCovid;user=sa;password=123456";
    }
    public Object [][] getdata(String query) throws SQLException{
        conn = DriverManager.getConnection(dbURL);
        if (conn != null) {
            System.out.println("Connected DB");
        }
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(query);
        int numcols = 6;
        int numrows = rs.last() ? rs.getRow() : 0;
        rs.beforeFirst();
        String[][] data = new String[numrows][numcols];
        int i = 0;
        while (rs.next()) {
            for(int j = 0; j<numcols; j++){
                data[i][j] = rs.getString(j+1);
            }
            i++;
        }
        conn.close();
        return data;
    }
}
