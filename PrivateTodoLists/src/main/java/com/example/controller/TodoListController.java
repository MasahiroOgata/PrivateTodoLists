package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.setting.service.SettingService;
import com.example.domain.todo.model.MTodo;
import com.example.domain.todo.service.TodoService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/todo/list")
@Slf4j
public class TodoListController {
	
	@Autowired
	private TodoService todoService;
	
	@Autowired 
	private SettingService settingService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("")
	public String showTodoList(Model model, @RequestParam(required = false) String search) {
		
//		if (Objects.isNull(session.getAttribute("isHidingFinishedTodo"))) {
//			session.setAttribute("isHidingFinishedTodo", 0);
//		}
		
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
		model.addAttribute("search", search);
		model.addAttribute("todoList", todoList);
		
		log.info(todoList.toString());
		
//		/* 画面設定サンプルコード */
//		List<MSetting> settingList = settingService.getSettingList();
//		log.info(settingList.toString());
//		Map<String, String> settingMap = new HashMap<>();
//		settingList.forEach(s -> settingMap.put(s.getCustomizeKey(), s.getCustomizeValue()));
//		log.info(settingMap.toString());
//		model.addAttribute("settingMap", settingMap);
		
		return "todo/list";
	}
	
//	@PostMapping("")
////	public @ResponseBody String toggleShowFinishedTodo(Model model, @RequestParam boolean state) {
//	public void toggleShowFinishedTodo(Model model, @RequestParam boolean state) {
//		
//		String CustomizeValue = state ? "1":"0";
//		settingService.setOneSetting("isHidingFinishedTodo", CustomizeValue);
//		
//		int nowShowingState = (int) session.getAttribute("isHidingFinishedTodo");
//		session.setAttribute("isHidingFinishedTodo", Math.abs(nowShowingState - 1));
		
//		List<MTodo> todoList = todoService.getTodoItems();
//		model.addAttribute("todoList", todoList);
		
		//return "redirect:/todo/list";
//	}
	
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
