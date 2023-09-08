package com.model;

public class User {
	private Integer Id;
	private String name;
	private String emailId;
	private String password;
	
	public User() {
	}
	
	public User(Integer id, String name, String emailId, String password) {
		Id = id;
		this.name = name;
		this.emailId = emailId;
		this.password = password;
	}
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
