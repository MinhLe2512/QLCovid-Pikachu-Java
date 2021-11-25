package com.example.qlcovid;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.example.qlcovid.jframe.UserFrame;
import com.example.qlcovid.jframe.ViewInfoFrame;
import com.example.qlcovid.model.UserAccount;

import com.example.qlcovid.jframe.LoginFrame;

public class CovidManagement {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("one");
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);

					System.out.println("2");
					//handleCases();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public static void handleCases() {
		
		UserAccount uAcc = new UserAccount();
		UserFrame uFrame = new UserFrame();
		//Click button show basic info
		uFrame.btnViewInfo.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewInfoFrame vFrame = new ViewInfoFrame();
				vFrame.setCid(uAcc.getCitizenId());
				vFrame.setFullname(uAcc.getFullName());
				vFrame.setDob(uAcc.getDateOfBirth());
				vFrame.setAddress(uAcc.getAddress());
				vFrame.setsetCondition(uAcc.getCondition());
			}
		});
	}

}
