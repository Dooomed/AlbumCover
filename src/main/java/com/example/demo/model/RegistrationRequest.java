package com.example.demo.model;

public class RegistrationRequest {
	
	private final String username;
	private final String password;
	private final String email;
	
	public RegistrationRequest(String username, String password, String email) {
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}
	
	
	
}
