package com.example.controller;

import java.io.File;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String getLogin(Model model) {
		
		File dir = new File("src/main/resources/static/img");
		String[] imgList = dir.list();
		Random random = new Random();
		int imgNum = random.nextInt(imgList.length);
		
		model.addAttribute("imgURL", imgList[imgNum]);
		
		return "login/login";
	}
	
}
