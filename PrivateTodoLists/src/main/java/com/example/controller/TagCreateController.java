package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.tag.model.MTag;
import com.example.domain.tag.service.IconService;
import com.example.domain.tag.service.TagService;
import com.example.form.TagForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tag/create")
public class TagCreateController {
	
	private final IconService iconService;
	
	private final ModelMapper modelMapper;
	
	private final TagService tagService;
		
	@GetMapping("")
	public String createTag(Model model, @ModelAttribute TagForm form) {
		model.addAttribute("iconList", iconService.getIconList());
		return "tag/create";
	}
	
	@PostMapping("")
	public String saveTag(Model model, @ModelAttribute @Validated TagForm form,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return createTag(model, form);
		}
		
		MTag tag = modelMapper.map(form, MTag.class);
		tagService.createOneTag(tag);	
		
		return "redirect:/tag/list";
	}

}
