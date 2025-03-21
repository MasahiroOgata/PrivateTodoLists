package com.example.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.todo.model.MTodo;
import com.example.domain.todo.service.TodoService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/todo/list")
@Slf4j
public class TodoListController {
	
	@Autowired
	TodoService todoService;
	
	@GetMapping("")
	public String showTodoList(Model model, HttpSession session) {
		
		List<MTodo> todoList = todoService.getTodoItems();
		
		if (Objects.isNull(session.getAttribute("isShowingFinishedTodo"))) {			
			session.setAttribute("isShowingFinishedTodo", true);
			
		}
		log.info(session.getAttribute("isShowingFinishedTodo").toString());
		
//		model.addAttribute("isShowingFinishedTodo", true);
		model.addAttribute("todoList", todoList);
		
		
		return "todo/list";
	}
	
	@PostMapping("")
	public @ResponseBody String showTodoListWithFinish(Model model,  @RequestParam(required = false) boolean state) {
		
		List<MTodo> todoList = todoService.getTodoItems();
		
		model.addAttribute("isShowingFinishedTodo", !state);
		model.addAttribute("todoList", todoList);
		
		//return "fragment :: #todo-table";
		return "todo/list";
	}
	
//	@PostMapping("")
//	public String toggleShowFinishedTodo(Model model, @RequestParam boolean state) {
////		
////		boolean isShowingFinishedTodo = (boolean) session.getAttribute("isShowingFinishedTodo");
////		model.addAttribute("isShowingFinishedTodo", !isShowingFinishedTodo);
//	
//		List<MTodo> todoList = todoService.getTodoItems();
//		model.addAttribute("isShowingFinishedTodo", !state);
//		model.addAttribute("todoList", todoList);
//		
////		
//		return "todo/list";
//	}
}
