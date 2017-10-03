package com.selsoft.trackme.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Sudhansu Sekhar
 *
 */
@Document(collection = "users")
public class User {

	private String firstName;
	private String lastName;
	@Id
	private String email;
	private String password;
	private boolean loggedOn;
	private String lastAccessed="";
	

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
	
	public boolean isLoggedOn() {
		return loggedOn;
	}

	public void setLoggedOn(boolean loggedOn) {
		this.loggedOn = loggedOn;
	}

	public String getLastAccessed() {
		return lastAccessed;
	}

	public void setLastAccessed(String lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", loggedOn=");
		builder.append(loggedOn);
		builder.append(", lastAccessed=");
		builder.append(lastAccessed);
		builder.append("]");
		return builder.toString();
	}

}
