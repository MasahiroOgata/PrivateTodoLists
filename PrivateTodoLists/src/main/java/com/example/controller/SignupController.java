package com.example.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.SignupForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignupController {
	
	private final UserService userService;
	
	private final ModelMapper modelMapper;
	
	@GetMapping("")
	public String getSignup(Model model, @ModelAttribute SignupForm form) {
		return "signup/signup";
	}
	
	@PostMapping("")
	public String postSignup(Model model,
			@ModelAttribute @Validated SignupForm form,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {			
			return getSignup(model, form);
		}
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		MUser user = modelMapper.map(form, MUser.class);
		
		userService.signupUser(user);
		
		return "redirect:/login";
	}

}
