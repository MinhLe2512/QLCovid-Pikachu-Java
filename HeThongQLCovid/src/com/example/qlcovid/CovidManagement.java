package com.example.qlcovid;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.example.qlcovid.jframe.UserFrame;
import com.example.qlcovid.jframe.ViewInfoFrame;
import com.example.qlcovid.model.UserAccount;

import Login.LoginFrame;

public class CovidManagement {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					//window.frame.setVisible(true);
					//new window then login handlecases 
					//cứ làm vầy vô handlecases new LoginFrame
					//LoginFrame nhập vô username, password
					//Check cái đó xong rồi ok thì duyệt qua xong rồi check role rồi check validation
					//xong rồi new account thích hợp
					//nếu new cái user account thì nhớ dùng cái string sqlSelect t để trong package string
					//để tiện khi mà user gọi view info mình ko phải check database nữa 
					handleCases();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void handleCases() {
		//if 1, 2, 3 gì gì đó xong rồi thì giải quyết từng chức năng
		//t demo cái user thôi
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
