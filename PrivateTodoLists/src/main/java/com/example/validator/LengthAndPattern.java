package com.example.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = LengthAndPatternValidator.class)
public @interface LengthAndPattern {
	
	String message() default "";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
		int max();
		int min();
		String regexp();
		
		@Target(FIELD)
		@Retention(RUNTIME)
		@Documented
		public @interface List {
			LengthAndPattern[] value();
		}

}
