package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.todo.model.MTodo;
import com.example.domain.todo.service.TodoService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo/list")
public class TodoListController {
	
	private final TodoService todoService;
	
	private final HttpSession session;
	
	@GetMapping("2")
	public String showTodoList(Model model, @RequestParam(required = false) String search,
			@PageableDefault(size = 10) Pageable pageable) {
		
		if ((String)session.getAttribute("loginMsg") != null) {
			model.addAttribute("flashMsg", (String)session.getAttribute("loginMsg"));
			session.removeAttribute("loginMsg");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date today = new Date();
		try {
			today= sdf.parse(sdf.format(today));
		} catch(ParseException e){
		}		
		model.addAttribute("today", today);
		
		List<MTodo> todoList = todoService.getTodoItems(search);
		Page<MTodo> todoListWithPage = todoService.getTodoItemsWithPages(search, pageable);
		model.addAttribute("search", search);
		model.addAttribute("todoList", todoList);
		model.addAttribute("todoListWithPage", todoListWithPage);
		
		return "todo/list";
	}
	
	@GetMapping("")
	public String showTodoList2(Model model, @RequestParam(required = false) String search,
			@PageableDefault(size = 10) Pageable pageable) {
		
		if ((String)session.getAttribute("loginMsg") != null) {
			model.addAttribute("flashMsg", (String)session.getAttribute("loginMsg"));
			session.removeAttribute("loginMsg");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date today = new Date();
		try {
			today= sdf.parse(sdf.format(today));
		} catch(ParseException e){
		}		
		model.addAttribute("today", today);
		
		int todayExpiringCount = todoService.getTodayExpiringTodoCount();
		Date newestDateTime = todoService.getNewestDateTime();
		//List<MTodo> todoList = todoService.getTodoItems(search);
		model.addAttribute("search", search);
		//model.addAttribute("todoList", todoList);
		model.addAttribute("todayExpiringCount", todayExpiringCount);
		model.addAttribute("newestDateTime", newestDateTime);
		
		return "todo/listb";
	}
	
}
