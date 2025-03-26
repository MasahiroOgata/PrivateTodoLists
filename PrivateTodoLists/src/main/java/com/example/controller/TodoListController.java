package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.todo.model.MTodo;
import com.example.domain.todo.service.TodoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/todo/list")
public class TodoListController {
	
	@Autowired
	TodoService todoService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("")
	public String showTodoList(Model model, @RequestParam(required = false) String search) {
		
		if (Objects.isNull(session.getAttribute("isShowingFinishedTodo"))) {
			session.setAttribute("isShowingFinishedTodo", 0);
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date today = new Date();
		try {
			today= sdf.parse(sdf.format(today));
		} catch(ParseException e){
		}		
		model.addAttribute("today", today);
		
		List<MTodo> todoList = todoService.getTodoItems();
		model.addAttribute("search", search);
		model.addAttribute("todoList", todoList);
		
		return "todo/list";
	}
	
	@PostMapping("")
//	public @ResponseBody String toggleShowFinishedTodo(Model model, @RequestParam boolean state) {
	public void toggleShowFinishedTodo(Model model, @RequestParam boolean state) {
			
		int nowShowingState = (int) session.getAttribute("isShowingFinishedTodo");
		session.setAttribute("isShowingFinishedTodo", Math.abs(nowShowingState - 1));
		
//		List<MTodo> todoList = todoService.getTodoItems();
//		model.addAttribute("todoList", todoList);
		
		//return "redirect:/todo/list";
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
