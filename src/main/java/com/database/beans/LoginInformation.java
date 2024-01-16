package com.database.beans;

import com.database.facades.ClientType;

public class LoginInformation {

	private String email;
	private String password;
	private ClientType type;

	public LoginInformation() {
		super();
	}

	public LoginInformation(String email, String password, ClientType type) {
		super();
		this.email = email;
		this.password = password;
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ClientType getType() {
		return type;
	}

	public void setType(ClientType type) {
		this.type = type;
	}

}
