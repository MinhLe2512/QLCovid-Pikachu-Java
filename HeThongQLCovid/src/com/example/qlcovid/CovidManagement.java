package com.example.qlcovid;

import com.example.qlcovid.jframe.Manager_addpatient;
import com.example.qlcovid.jframe.Manager_mainframe;
import com.example.qlcovid.model.ManagerDB;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class CovidManagement {
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
                String MgID = "3112";
                Manager_mainframe mf = new Manager_mainframe(MgID);
                mf.setVisible(true);
                
	}
}
