package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.todo.service.TodoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("todo/delete")
public class TodoDeleteController {
	
	private final TodoService todoService;
	
	@PostMapping("{id}")
	public String deleteTodo(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		
		todoService.deleteOneTodo(id);
		
		redirectAttributes.addFlashAttribute("flashMsg", "項目を削除しました");
		
		return "redirect:/todo/list";
	}

}
