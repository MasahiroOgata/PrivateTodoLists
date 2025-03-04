package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo/create")
public class TodoCreateController {
	
	@GetMapping("")
	public String createTodo() {
		
		return "todo/create";
	}

}
