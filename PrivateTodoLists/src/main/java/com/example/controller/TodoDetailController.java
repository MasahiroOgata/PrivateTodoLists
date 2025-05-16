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

import com.example.domain.tag.service.TagService;
import com.example.domain.todo.model.MTodo;
import com.example.domain.todo.service.TodoService;
import com.example.form.TodoForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo/detail")
public class TodoDetailController {
	
	private final TodoService todoService;
	
	private final TagService tagService;
	
	private final ModelMapper modelMapper;
	
	@GetMapping("{id}")
	public String showTodoDetail(Model model, @ModelAttribute TodoForm form) {
		
		MTodo todo = todoService.getOneTodo(form.getId());
		
		if(form.getRegistrationDate() == null) {
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			form = modelMapper.map(todo, TodoForm.class);
		}
		
		model.addAttribute("tagList", tagService.getTagItems());
		model.addAttribute("previousTodo", todo);
		model.addAttribute("todoForm", form);
		
		return "todo/detail";
	}
	
	@PostMapping("{id}")
	public String editOneTodo(Model model, @ModelAttribute @Validated TodoForm form, 
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return showTodoDetail(model, form);
		}
		
		MTodo todo = modelMapper.map(form, MTodo.class);
		todoService.editOneTodo(todo);
		 
		 return "redirect:/todo/list";
	}

}
