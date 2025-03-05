package com.example.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.user.service.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoExistingUserIdValidator implements ConstraintValidator<NoExistingUserId, String> {
	
	@Autowired
	private UserService userService;
	
	public void initialize(NoExistingUserId constraintAnnotation) {}
	
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return !(userService.isRegisterdUserId(value));
	}

}
