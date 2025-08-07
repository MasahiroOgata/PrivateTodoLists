package com.example.rest;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.setting.service.SettingService;
import com.example.domain.todo.model.MTodo;
import com.example.domain.todo.service.TodoService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoRestController {
	
	private final SettingService settingService;
	private final TodoService todoService;
	
	@PutMapping("/toggle")
	public int toggleHideFinishedTodo(boolean state) {
		String CustomizeValue = state ? "1":"0";
		settingService.setOneSetting("isHidingFinishedTodo", CustomizeValue);
		
		return 0;
	}
	
	@PutMapping("/finishtoggle")
	public int toggleTodoFinishUnfinish(int todoId) {
		MTodo todo = todoService.getOneTodo(todoId);
		
		if (todo.getFinishedDate() == null) {
			todo.setFinishedDate(new Date());
		} else {
			todo.setFinishedDate(null);
		}
		
		todoService.editOneTodo(todo);
		
		return 0;		
	}
	
	@GetMapping("get/list")
	public List<MTodo> getTodoList() {
		List<MTodo> todoList = todoService.getTodoItems(null);
		
		return todoList;
	}
	
}
