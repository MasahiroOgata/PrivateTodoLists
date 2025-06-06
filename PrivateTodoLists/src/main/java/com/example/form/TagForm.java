package com.example.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TagForm {
	
	private Integer id;
	
	@NotBlank
	@Length(max = 20)
	private String tagName;
	
	private String tagColor;
	
	@NotNull
	private String tagIcon;

}
