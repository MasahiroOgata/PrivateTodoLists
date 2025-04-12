package com.example.domain.todo.model;

import java.util.Date;

import com.example.domain.tag.model.MTag;

import lombok.Data;

@Data
public class MTodo {
	private Integer id;
	private Integer userId;
	private String itemName;
	private Date registrationDate;
	private Date expireDate;
	private Date finishedDate;
	private Integer tagId;
	private MTag tag;
}
