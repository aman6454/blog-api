package com.blogapi.blogapi.payload;

public class JWTAuthRequest {
	
	private String username;
	private String password;
	
	public JWTAuthRequest() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
