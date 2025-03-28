package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.todo.model.MTodo;
import com.example.domain.todo.service.TodoService;

@Controller
@RequestMapping("/todo/calendar")
public class TodoCalendarController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("")
	public String showCalendar(Model model) {
		
		List<MTodo> todoList = todoService.getTodoItems(null);
		
		model.addAttribute("todoList", todoList);
		
		return "todo/calendar";
	}

}
