package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.tag.model.MTag;
import com.example.domain.tag.service.TagService;

@Controller
@RequestMapping("/tag/list")
public class TagListController {
	
	@Autowired
	private TagService tagService;
	
	@GetMapping("")
	public String showTagList(Model model) {
		
		List<MTag> tagList = tagService.getTagItems();
		model.addAttribute("tagList", tagList);
		
		return "tag/list";
	}

}
