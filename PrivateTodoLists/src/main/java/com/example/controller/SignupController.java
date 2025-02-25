package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.form.SignupForm;

@Controller
public class SignupController {
	
	@GetMapping("/signup")
	public String getSignup(Model model, @ModelAttribute SignupForm form) {
		return "signup/signup";
	}
	
	@PostMapping("/signup")
	public String postSignup(Model model, @ModelAttribute SignupForm form) {
		
		return "signup/signup";
	}

}
