package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.TagForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/tag/create")
@Slf4j
public class TagCreateController {
	
	private String[] iconList = {
		"fa-solid fa-star",
		"fa-solid fa-heart",
		"fa-solid fa-circle-check",
		"fa-solid fa-bell",
		"fa-solid fa-envelope",
		"fa-solid fa-phone",
		"fa-solid fa-pen",
		"fa-solid fa-building",
		"fa-solid fa-school",
		"fa-solid fa-hospital",
		"fa-solid fa-cart-shopping",
		"fa-solid fa-utensils",
		"fa-solid fa-yen-sign",
		"fa-solid fa-gift"
	};
	
	@GetMapping("")
	public String createTag(Model model, @ModelAttribute TagForm form) {
		model.addAttribute("iconList", iconList);
		return "tag/create";
	}
	
	@PostMapping("")
	public String saveTag(Model model, @ModelAttribute @ Validated TagForm form) {
		
		log.info(form.toString());
		
		return "redirect:/tag/create";
	}

}
