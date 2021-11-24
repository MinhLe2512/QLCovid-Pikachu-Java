package com.example.qlcovid;

import java.awt.EventQueue;

import Login.LoginFrame;

public class CovidManagement {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					//window.frame.setVisible(true);
					//new window then login here
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
