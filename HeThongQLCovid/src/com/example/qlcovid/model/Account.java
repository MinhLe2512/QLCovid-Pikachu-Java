package com.example.qlcovid.model;

import Login.Hashing;

public abstract class Account {
	private String username, password, role;
	
	Account(String username, String password){
		this.username = username;
		this.password = password;
	}
	Account(){
		this.username = "";
		this.password = "";
	}

	public String getUsername() {return username;}

	public void setUsername(String username) {this.username = username;}

	public String getPassword() {return password;}

	public void setPassword(String password) {this.password = password;}
	
	public String getRole() {return role;}

	public void setRole(String role) {this.role = role;}
	
	public String hashedPassword() {return Hashing.getHash(password);}
	
}
