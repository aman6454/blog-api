package com.blogapi.blogapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogapi.blogapi.exception.ResourceNotFoundException;
import com.blogapi.blogapi.model.User;
import com.blogapi.blogapi.repository.UserRepository;

@Service	
public class CustomUserDetailSevice implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByEmail(username)
		.orElseThrow(()-> new ResourceNotFoundException("User", "Email: "+username, username));
		return user;
	}

}
