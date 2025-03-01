package com.example.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.SignupForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SignupController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/signup")
	public String getSignup(Model model, @ModelAttribute SignupForm form) {
		return "signup/signup";
	}
	
	@PostMapping("/signup")
	public String postSignup(Model model,
			@ModelAttribute @Validated SignupForm form,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {			
			return getSignup(model, form);
		}
		
		log.info(form.toString());
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		MUser user = modelMapper.map(form, MUser.class);
		
		userService.signupUserAndCreateOwnTable(user);
		
		log.info(user.toString());
		
		return "redirect:/login";
	}

}
