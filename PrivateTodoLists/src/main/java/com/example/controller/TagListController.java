package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.tag.model.MTag;
import com.example.domain.tag.service.TagService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tag/list")
public class TagListController {
	
	private final TagService tagService;
	
	@GetMapping("")
	public String showTagList(Model model) {
		
		List<MTag> tagList = tagService.getTagItems();
		model.addAttribute("tagList", tagList);
		
		return "tag/list";
	}

}
