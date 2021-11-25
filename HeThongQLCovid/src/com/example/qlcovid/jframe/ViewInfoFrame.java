package com.example.qlcovid.jframe;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.time.LocalDate;

public class ViewInfoFrame extends JFrame{
	private JTextField txtCId;
	private JTextField txtFullname;
	private JTextField txtDob;
	private JTextField txtAddress;
	private JTextField txtCondition;
	public ViewInfoFrame() {
		getContentPane().setLayout(null);
		
		txtCId = new JTextField();
		txtCId.setBounds(43, 21, 96, 19);
		getContentPane().add(txtCId);
		txtCId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Citizen id");
		lblNewLabel.setBounds(43, 10, 45, 13);
		getContentPane().add(lblNewLabel);
		
		txtFullname = new JTextField();
		txtFullname.setColumns(10);
		txtFullname.setBounds(43, 61, 96, 19);
		getContentPane().add(txtFullname);
		
		JLabel lblFullName = new JLabel("Full name");
		lblFullName.setBounds(43, 50, 56, 13);
		getContentPane().add(lblFullName);
		
		txtDob = new JTextField();
		txtDob.setColumns(10);
		txtDob.setBounds(43, 103, 96, 19);
		getContentPane().add(txtDob);
		
		JLabel lblDateOfBirth = new JLabel("Date of birth");
		lblDateOfBirth.setBounds(43, 90, 75, 13);
		getContentPane().add(lblDateOfBirth);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(43, 148, 96, 19);
		getContentPane().add(txtAddress);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(43, 132, 75, 13);
		getContentPane().add(lblAddress);
		
		txtCondition = new JTextField();
		txtCondition.setFont(new Font("Tahoma", Font.PLAIN, 60));
		txtCondition.setBounds(255, 61, 101, 84);
		getContentPane().add(txtCondition);
		txtCondition.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Condition");
		lblNewLabel_1.setBounds(255, 50, 45, 13);
		getContentPane().add(lblNewLabel_1);
	}
	public void setCid(String id) {
		txtCId.setText(id);
	}
	public void setFullname(String fullname) {
		txtFullname.setText(fullname);
	}
	public void setDob(LocalDate dob) {
		txtDob.setText(dob.toString());
	}
	public void setAddress(String address) {
		txtAddress.setText(address);
	}
	public void setsetCondition(String condition) {
		txtCondition.setText(condition);
	}
}
