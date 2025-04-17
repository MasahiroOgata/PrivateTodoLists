package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.tag.model.MTag;
import com.example.domain.tag.service.IconService;
import com.example.domain.tag.service.TagService;
import com.example.form.TagForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/tag/create")
@Slf4j
public class TagCreateController {
	
	@Autowired
	private IconService iconService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TagService tagService;
		
	@GetMapping("")
	public String createTag(Model model, @ModelAttribute TagForm form) {
		model.addAttribute("iconList", iconService.getIconList());
		return "tag/create";
	}
	
	@PostMapping("")
	public String saveTag(Model model, @ModelAttribute @ Validated TagForm form) {
		
		log.info(form.toString());
		
		MTag tag = modelMapper.map(form, MTag.class);
		tagService.createOneTag(tag);	
		
		log.info(tag.toString());
		
		return "redirect:/tag/create";
	}

}
