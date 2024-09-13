package com.ktpl.mitadt.dto;

public class AuthenticateDto {


	private String login;
	private String password;

	public AuthenticateDto() {

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
