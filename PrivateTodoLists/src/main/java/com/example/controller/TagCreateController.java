package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tag/create")
public class TagCreateController {
	
	@GetMapping("")
	public String createTag(Model model) {
		return "tag/create";
	}

}
