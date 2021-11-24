package com.example.qlcovid.model;

import Login.Hashing;

public abstract class Account {
	private String username, password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String hashedPassword() {
		return Hashing.getHash(password);
	}
	
}
