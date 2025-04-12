package com.example.domain.tag.model;

import lombok.Data;

@Data
public class MTag {
	private Integer id;
	private Integer userId;
	private String tagName;
	private String tagColor;
	private String tagIcon;
}
