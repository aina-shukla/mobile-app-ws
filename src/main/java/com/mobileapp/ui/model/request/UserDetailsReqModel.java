package com.mobileapp.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailsReqModel {

	@NotNull(message="first name can't be null")
	private String firstName;
	
	@NotNull(message="last name can't be null")
	private String lastName;
	
	@NotNull(message="email can't be null")
	@Email
	private String email;
	
	@NotNull(message="password can't be null")
	@Size(min = 8, max = 16, message="password must be greater than 8 and less than 16 characters")
	private String password;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
}
