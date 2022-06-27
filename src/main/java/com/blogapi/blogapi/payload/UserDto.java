package com.blogapi.blogapi.payload;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class UserDto {

	@NotEmpty
	@Size(min = 4,max = 20,message = "Username must be minimum size 4 and maximum size 20 !!")
	private String name;
	@Email(message = "Please Enter valid Email !!")
	private String email;
	@NotEmpty
	@Size(min = 3,max = 12,message = "Password must be minimum length 3 and maximum length 12 !!")
	private String password;
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
	
	public UserDto() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}
	
}
