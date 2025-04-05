package com.example.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.setting.service.SettingService;
import com.example.form.SettingForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("setting")
public class SettingController {
	
	@Autowired
	SettingService settingService;
	
//	@Autowired
//	ResourceLoader resourceLoader;
	
	@GetMapping("")
	public String customizeSettings(Model model, @ModelAttribute SettingForm form) {
		
		File dir = new File("src/main/resources/static/img");
		String[] imgList = dir.list();
		
		//resourceLoader.getResource("classpath:" +"/static/img/");
		
		model.addAttribute("imgList", imgList);
		
		return "setting/setting";
	}
	
	@PostMapping("")
	public String saveSettings(Model model, @ModelAttribute SettingForm form,
			RedirectAttributes redirectAttributes) throws IllegalAccessException {
		
		log.info(form.toString());
		
		settingService.setAllSettings(form);

		redirectAttributes.addFlashAttribute("flashMsg", "設定を変更しました");

		
		return "redirect:/setting";
	}

}
