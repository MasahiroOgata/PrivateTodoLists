package com.example.form;

import lombok.Data;

@Data
public class SettingForm {
	
	private String headerBgColor;
	
	private String headerFontColor;
	
	private String backgroundImg;
	
	
	SettingForm() {
		this.headerBgColor = "#ffffff";
		this.headerFontColor = "#ffffff";
	}
	

}
