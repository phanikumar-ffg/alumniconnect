package com.drrf.alumniconnect.model;

public class UserDetails {

	private String email;
	private String password;
	private String role;
	private String message;
	
	
	
	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDetails(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public UserDetails(String email, String password, String role, String message) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
		this.message = message;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "UserDetails [email=" + email + ", role=" + role + ", message=" + message + "]";
	}
	
	
}
