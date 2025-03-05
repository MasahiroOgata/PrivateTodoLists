package com.example.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.TodoForm;

@Controller
@RequestMapping("/todo/create")
public class TodoCreateController {
	
	@GetMapping("")
	public String createTodo(Model model, @ModelAttribute TodoForm form) {
		
		if (form.getExpireDate() == null) {
			form.setExpireDate(new Date());
		}
		
		return "todo/create";
	}
	
	@PostMapping("")
	public String createTodo(Model model, @ModelAttribute @Validated TodoForm form, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return createTodo(model,form);
		}
		
		return "redirect:/todo/list";
	}

}
