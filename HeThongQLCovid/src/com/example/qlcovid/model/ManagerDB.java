/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.qlcovid.model;

import com.example.qlcovid.jframe.ManagerUI;
import java.sql.*;


/**
 *
 * @author nhonnhon
 */
public class ManagerDB {
    ManagerUI mgUI;
    Connection conn;
    String dburl = "jdbc:sqlserver://localhost";
    String user = "sa";
    String password = "123456";
    String databaseName = "QLCovid";
    public ManagerDB(){
    }
    public Object [][] getdata(String query, int table) throws SQLException{
        System.out.println(query);
        conn = DriverManager.getConnection(dburl+";databaseName=" + databaseName + ";user=" + user +";password=" + password);
        if (conn != null) {
            System.out.println("Connected DB");
        }
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(query);
        int numcols = table == 1? 7:6;
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
