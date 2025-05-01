package com.example.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.setting.service.SettingService;

@RestController
@RequestMapping("/todo")
public class TodoRestController {
	
	@Autowired
	private SettingService settingService;
	
	@PutMapping("/toggle")
	public int toggleHideFinishedTodo(boolean state) {
		String CustomizeValue = state ? "1":"0";
		settingService.setOneSetting("isHidingFinishedTodo", CustomizeValue);
		
		return 0;
	}
	
	

}
