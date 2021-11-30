package com.example.qlcovid;

import com.example.qlcovid.jframe.Manager_mainframe;
import com.example.qlcovid.model.ManagerDB;
import java.sql.SQLException;


public class CovidManagement {
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
                Manager_mainframe mf = new Manager_mainframe();
                mf.setVisible(true);
                ManagerDB db = new ManagerDB();
	}
}
