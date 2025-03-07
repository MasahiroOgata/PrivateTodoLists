package com.example.domain.todo.model;

import java.util.Date;

import lombok.Data;

@Data
public class MTodo {
	private Integer id;
	private String itemName;
	private Date registrationDate;
	private Date expireDate;
	private Date finishedDate;	
}
