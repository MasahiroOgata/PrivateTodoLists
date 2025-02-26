package com.example.form;

import com.example.validator.LengthAndPattern;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupForm {
	
	@NotBlank//(groups = ValidGroup1.class)
	private String userId;
	
	@NotBlank//(groups = ValidGroup1.class)
	@LengthAndPattern(min = 4, max = 100, regexp = "^[a-zA-Z0-9]+$")
	//@Length(min = 4, max = 100)//, groups = ValidGroup2.class)
	//@Pattern(regexp = "^[a-zA-Z0-9]+$")//, groups = ValidGroup2.class)
	private String password;
	
	@NotBlank//(groups = ValidGroup1.class)
	private String userName;

}
