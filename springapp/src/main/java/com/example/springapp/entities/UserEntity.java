package com.example.BaitAndTackleModified.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {
	
	
	public UserEntity() {}
	
	public UserEntity(String email, String password, String contact, String username, String gender) {
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.username = username;
		this.gender = gender;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long user_id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "contact")
	private String contact;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "gender")
	private String gender;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "UserEntity [user_id=" + user_id + ", email=" + email + ", password=" + password + ", contact=" + contact
				+ ", username=" + username + ", gender=" + gender + "]";
	}
	
	
	
	
	
	
}
