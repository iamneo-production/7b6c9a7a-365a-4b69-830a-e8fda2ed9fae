package com.example.BaitAndTackleModified.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LoginModel {

	
	@NotNull(message = "email should not be null")
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "invalid email format entered")
	private String email;
	
	@NotNull(message = "password should not be null")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "password should contain minimum eight characters, at least one letter and one number")
	private String password;
	
	
	public LoginModel() {}
	
	public LoginModel(
			@NotNull(message = "email should not be null") @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "invalid email format entered") String email,
			@NotNull(message = "password should not be null") @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{8,}$", message = "password should contain minimum eight characters, at least one letter and one number") String password) {
		this.email = email;
		this.password = password;
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

	@Override
	public String toString() {
		return "LoginModel [email=" + email + ", password=" + password + "]";
	}
	

	
	
}
