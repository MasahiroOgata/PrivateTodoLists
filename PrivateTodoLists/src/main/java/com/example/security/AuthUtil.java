package com.example.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.domain.user.service.impl.UserWithNameAndId;

public class AuthUtil {
	
	public static int getLoginUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserWithNameAndId userWithNameAndId = (UserWithNameAndId) authentication.getPrincipal();
		return userWithNameAndId.getId();
	}

}
