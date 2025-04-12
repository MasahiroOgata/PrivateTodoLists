package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tag/list")
public class TagListController {
	
	@GetMapping("")
	public String showTagList(Model model) {
		return "tag/list";
	}

}
