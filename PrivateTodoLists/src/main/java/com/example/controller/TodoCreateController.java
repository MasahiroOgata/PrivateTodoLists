package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.domain.setting.service.SettingService;
import com.example.domain.todo.model.MTodo;
import com.example.domain.todo.service.TodoService;
import com.example.form.TodoForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/todo/create")
@Slf4j
public class TodoCreateController {
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private SettingService settingService;
	
	@Autowired
	private ModelMapper modelMapper;
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		binder.setAllowedFields("itemName", "registrationDate", "expireDate", "finishedDate");
//	}
	
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
			//form.setExpireDate(java.sql.Date.valueOf(expireDate));			
		}
		if (form.getExpireDate() == null) {
			form.setExpireDate(new Date());
		}
		
		return "todo/create";
	}
	
	@PostMapping("")
	public String saveTodo(@RequestParam(required = false) String expireDate, 
			Model model, @ModelAttribute @Validated TodoForm form, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("settingMap", settingService.getSettingMap());
			model.addAttribute("unfinishedTodoCount", todoService.getUnfinishedTodoCount());
			return createTodo(expireDate, model, form);
		}
		
		log.info(form.toString());
		
		form.setRegistrationDate(new Date());
		MTodo todo = modelMapper.map(form, MTodo.class);
		
		todoService.createOneTodo(todo);
		
		redirectAttributes.addFlashAttribute("flashMsg", "作業を登録しました");
		redirectAttributes.addFlashAttribute("newTodoId", todo.getId());
		
		return "redirect:/todo/list";
	}

}
