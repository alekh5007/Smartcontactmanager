package com.smart.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.dao.UserRepo;
import com.smart.entities.User;

public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//fetching user from database
		
		User userByUserName = userRepo.getUserByUserName(username);
		if(userByUserName==null)
		{
			throw new UsernameNotFoundException("could not  not found");
		}
		
		CustomUserDetails customUserDetails = new CustomUserDetails(userByUserName);
		
		return customUserDetails;
	}

}
