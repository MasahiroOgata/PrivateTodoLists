package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.todo.service.TodoService;

@Controller
@RequestMapping("todo/delete")
public class TodoDeleteController {
	
	@Autowired
	TodoService todoService;
	
	@PostMapping("{id}")
	public String deleteTodo(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		
		todoService.deleteOneTodo(id);
		
		redirectAttributes.addFlashAttribute("flashMsg", "項目を削除しました");
		
		return "redirect:/todo/list";
	}

}
