package com.example.form;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class TagForm {
	
	private Integer id;
	
	@Length(max = 15)
	private String tagName;
	
	private String tagColor;
	
	private String tagIcon;

}
