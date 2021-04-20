package com.onlineclinicsystem;

public class AuthenticationRequest {

	private String email;
	private String password;
	private String roles;

	public AuthenticationRequest() {
	}

	public AuthenticationRequest(String email, String password, String roles) {
		this.email = email;
		this.password = password;
		this.roles = roles;
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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}
