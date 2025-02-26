package com.example.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LengthAndPatternValidator implements ConstraintValidator<LengthAndPattern, String> {
	
	private int max;
	private int min;
	private String regexp;
	
	public void initialize(LengthAndPattern constraintAnnotation) {
		this.max = constraintAnnotation.max();
		this.min = constraintAnnotation.min();
		this.regexp = constraintAnnotation.regexp();
	}
	
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return (value.length() >= min && value.length() <= max && value.matches(regexp));
	}

}
