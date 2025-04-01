package com.example.controller;

import java.lang.reflect.Field;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.setting.model.MSetting;
import com.example.form.SettingForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("setting")
public class SettingController {
	
	@GetMapping("")
	public String customizeSettings(Model model, @ModelAttribute SettingForm form) {
		return "setting/setting";
	}
	
	@PostMapping("")
	public String saveSettings(Model model, @ModelAttribute SettingForm form) throws IllegalAccessException {
		
		log.info(form.toString());
		
		Class<?> clazz = form.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			MSetting setting = new MSetting();
			setting.setCustomizeKey(field.getName());
			setting.setCustomizeValue(field.get(form).toString());
			
			log.info(setting.toString());
		}
		
		return "redirect:/setting";
	}

}
