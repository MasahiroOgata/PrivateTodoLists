package com.example.controller;

import java.io.File;

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

import com.example.domain.setting.service.SettingService;
import com.example.domain.tag.model.MTag;
import com.example.domain.tag.service.IconService;
import com.example.domain.tag.service.TagService;
import com.example.domain.todo.service.TodoService;
import com.example.form.TagForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("tag/detail")
public class TagDetailController {
	
	@Autowired
	private IconService iconService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private SettingService settingService;

	
	@GetMapping("{id}")
	public String showTagDetail(Model model, @ModelAttribute TagForm form) {
		
		MTag tag = tagService.getOneTag(form.getId());
		
		if (form.getTagColor() == null) {
			form  = modelMapper.map(tag, TagForm.class);
		}
		
		model.addAttribute("iconList", iconService.getIconList());
		model.addAttribute("previousTag", tag);
		model.addAttribute("tagForm", form);
		
		return "tag/detail";
	}
	
	@PostMapping("{id}")
	public String editOneTag(Model model, @ModelAttribute @Validated TagForm form, BindingResult bindingResult) {
		
		log.info(form.toString());
		
		if (bindingResult.hasErrors()) {
			File dir = new File("src/main/resources/static/img");
			model.addAttribute("imgList", dir.list());	
			model.addAttribute("settingMap", settingService.getSettingMap());
			model.addAttribute("unfinishedTodoCount", todoService.getUnfinishedTodoCount());
			return showTagDetail(model, form);
		}
		
		MTag tag = modelMapper.map(form, MTag.class);
		tagService.editOneTag(tag);
		
		return "redirect:/tag/list";
		
	}
}
