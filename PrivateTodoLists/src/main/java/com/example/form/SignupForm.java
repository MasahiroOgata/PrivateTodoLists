package com.example.form;

import org.hibernate.validator.constraints.Length;

import com.example.validator.LengthAndPattern;
import com.example.validator.NoExistingUserId;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupForm {
	
	@NotBlank
	@NoExistingUserId
	@Length(max = 50)
	private String userId;
	
	@LengthAndPattern(min = 4, max = 100, regexp = "^[a-zA-Z0-9]+$")	
	private String password;
	
	@NotBlank
	@Length(max = 50)
	private String userName;

}
