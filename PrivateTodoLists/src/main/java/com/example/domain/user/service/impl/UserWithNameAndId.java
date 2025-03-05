package com.example.domain.user.service.impl;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserWithNameAndId extends User {
	
	private Integer id;
	private String userName;
	
	public UserWithNameAndId(String username, String password,
			Collection<? extends GrantedAuthority> authorities,
			Integer id, String userName) {
		super(username, password, authorities);
		this.id = id;
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}
	
	

}
