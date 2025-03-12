package com.example.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.todo.model.MTodo;
import com.example.domain.todo.service.TodoService;

@Controller
@RequestMapping("/todo/finish")
public class TodoFinishController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("{id}")
	public String finishTodo(@PathVariable("id") int id) {
		
		MTodo todo = todoService.getOneTodo(id);
		
		if (todo.getFinishedDate() == null) {
			todo.setFinishedDate(new Date());
		} else {
			todo.setFinishedDate(null);
		}
		
		todoService.editOneTodo(todo);
		
		return "redirect:/todo/list";
	}

}
