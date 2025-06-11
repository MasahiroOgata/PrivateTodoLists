package com.example.domain.tag.service;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class IconService {
	
	public static List<String> getIconList() {
		return List.of(
			"fa-solid fa-star",
			"fa-solid fa-heart",
			"fa-solid fa-circle-check",
			"fa-solid fa-circle-exclamation",
			"fa-solid fa-bell",
			"fa-solid fa-envelope",
			"fa-solid fa-phone",
			"fa-solid fa-pen",
			"fa-solid fa-house-user",
			"fa-solid fa-building",
			"fa-solid fa-school",
			"fa-solid fa-hospital",
			"fa-solid fa-scissors",
			"fa-solid fa-trash",
			"fa-solid fa-cart-shopping",
			"fa-solid fa-utensils",
			"fa-solid fa-yen-sign",
			"fa-solid fa-cake-candles",
			"fa-solid fa-gift",		
			"fa-solid fa-book",
			"fa-solid fa-gamepad",
			"fa-solid fa-tv",
			"fa-solid fa-music",
			"fa-solid fa-ticket",
			"fa-solid fa-person-running"
		);
	}

}
