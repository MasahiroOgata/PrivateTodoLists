package com.example.rest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.setting.service.SettingService;
import com.example.domain.tag.service.TagService;
import com.example.domain.todo.model.MTodo;
import com.example.domain.todo.service.TodoService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoRestController {
	
	private final SettingService settingService;
	private final TodoService todoService;
	private final TagService tagService;
	
	@PutMapping("/tabletoggle")
	public int toggleHideFinishedTodo(boolean state) {
		String CustomizeValue = state ? "1":"0";
		settingService.setOneSetting("isHidingFinishedTodo", CustomizeValue);
		
		return 0;
	}
	
	@PutMapping("/finishtoggle")
	public MTodo toggleTodoFinishUnfinish(int todoId) {
		MTodo todo = todoService.getOneTodo(todoId);
		
		if (todo.getFinishedDate() == null) {
			todo.setFinishedDate(new Date());
		} else {
			todo.setFinishedDate(null);
		}
		
		todoService.editOneTodo(todo);
		if (todo.getTagId() != null) {
			todo.setTag(tagService.getOneTag(todo.getTagId()));
		}
		
		return todo;		
	}
	
	@GetMapping("get/list")
	public List<MTodo> getTodoList(boolean state, String search) {
		List<MTodo> todoList = todoService.getTodoItems(search);
		
		if (state) {
			List<MTodo> finishedTodoList = todoList.stream()
					.filter(todo -> todo.getFinishedDate() == null)
					.collect(Collectors.toList());
			return finishedTodoList;			
		} else {
			return todoList;
		}
	}
	
	@GetMapping("fetchnewestdate")
	public Date fetchNewestDate() {
		return todoService.getNewestDateTime();
	}
	
}
