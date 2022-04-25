package com.example.BaitAndTackleModified.models;

public class SignupModel {
	
	public SignupModel(String email, String password, String confirmPassword, String contact, String userName,
			String gender) {
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.contact = contact;
		this.userName = userName;
		this.gender = gender;
	}

	public SignupModel() {}
	
	
	
	
	private String email;
	private String password;
	private String confirmPassword;
	private String contact;
	private String userName;
	private String gender;
	
	
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "SignupModel [email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", contact=" + contact + ", userName=" + userName + ", gender=" + gender + "]";
	}
	
}
