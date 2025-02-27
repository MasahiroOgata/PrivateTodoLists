package com.example.domain.todo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.todo.service.TodoService;
import com.example.repository.TodoMapper;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoMapper todoMapper;
	
	@Override
	/** ユーザー固有のTODOテーブルを作成 */
	public void makeUserOwnTable(int userId) {
		todoMapper.createUserOwnTable(userId);
	}

}
