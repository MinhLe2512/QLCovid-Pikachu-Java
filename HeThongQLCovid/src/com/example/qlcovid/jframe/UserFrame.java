package com.example.qlcovid.jframe;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class UserFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel titleLabel;
	private JButton btnViewInfo;
	public UserFrame() {
		getContentPane().setLayout(null);
		
		titleLabel = new JLabel("Title");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titleLabel.setBounds(160, 10, 115, 42);
		getContentPane().add(titleLabel);
		
		btnViewInfo = new JButton("View info");
		btnViewInfo.setBounds(160, 87, 85, 21);
		btnViewInfo.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		getContentPane().add(btnViewInfo);
	}
	
	public void setTitle(String toSet) {
		titleLabel.setText(toSet);		
	}
}
