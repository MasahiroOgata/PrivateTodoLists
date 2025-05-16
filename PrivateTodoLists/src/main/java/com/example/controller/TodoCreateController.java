package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.tag.service.TagService;
import com.example.domain.todo.model.MTodo;
import com.example.domain.todo.service.TodoService;
import com.example.form.TodoForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo/create")
public class TodoCreateController {
	
	private final TodoService todoService;
	
	private final TagService tagService;
	
	private final ModelMapper modelMapper;

	@GetMapping("")
	public String createTodo(@RequestParam(required = false) String expireDate,
			Model model, @ModelAttribute TodoForm form) {
		
		if (expireDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-");
			try  {
				Date newExpireDate = sdf.parse(expireDate);
				form.setExpireDate(newExpireDate);
			} catch (ParseException e) {
			}
		}
		if (form.getExpireDate() == null) {
			form.setExpireDate(new Date());
		}
		
		model.addAttribute("tagList", tagService.getTagItems());
		
		return "todo/create";
	}
	
	@PostMapping("")
	public String saveTodo(	Model model, @ModelAttribute @Validated TodoForm form, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return createTodo(form.getExpireDate().toString(), model, form);
		}
		
		form.setRegistrationDate(new Date());
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		MTodo todo = modelMapper.map(form, MTodo.class);
		
		todoService.createOneTodo(todo);
		
		redirectAttributes.addFlashAttribute("flashMsg", "作業を登録しました");
		redirectAttributes.addFlashAttribute("newTodoId", todo.getId());
		
		return "redirect:/todo/list";
	}

}
