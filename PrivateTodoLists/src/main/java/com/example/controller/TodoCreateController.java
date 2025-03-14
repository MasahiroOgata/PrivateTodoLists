package com.example.controller;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.todo.model.MTodo;
import com.example.domain.todo.service.TodoService;
import com.example.form.TodoForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/todo/create")
@Slf4j
public class TodoCreateController {
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("")
	public String createTodo(Model model, @ModelAttribute TodoForm form) {
		
		if (form.getExpireDate() == null) {
			form.setExpireDate(new Date());
		}
		
		return "todo/create";
	}
	
	@PostMapping("")
	public String createTodo(Model model, @ModelAttribute @Validated TodoForm form, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return createTodo(model,form);
		}
		
		log.info(form.toString());
		
		form.setRegistrationDate(new Date());
		MTodo todo = modelMapper.map(form, MTodo.class);
		
		todoService.createOneTodo(todo);
		
		redirectAttributes.addFlashAttribute("flashMsg", "作業を登録しました");
		
		return "redirect:/todo/list";
	}

}
