package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.todo.model.MTodo;
import com.example.domain.todo.service.TodoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo/calendar")
public class TodoCalendarController {
	
	private final TodoService todoService;
	
	@GetMapping("")
	public String showCalendar(Model model) {
		
		List<MTodo> todoList = todoService.getTodoItems(null);
		
		model.addAttribute("todoList", todoList);
		
		return "todo/calendar";
	}

}
