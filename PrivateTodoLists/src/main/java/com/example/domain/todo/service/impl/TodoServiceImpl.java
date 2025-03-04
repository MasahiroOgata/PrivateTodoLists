package com.example.domain.todo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.todo.service.TodoService;
import com.example.repository.TodoMapper;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoMapper todoMapper;
	
	/** ユーザー固有のTODOテーブルを作成 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void makeUserOwnTable(int userId) {
		todoMapper.createUserOwnTable(userId);
	}
	
	/** 作成された固有テーブルの存在を確認 */
	@Override
	public boolean existsUserOwnTable(int userId) {
		return todoMapper.countUserOwnTable(userId) == 1;
	}
	


}
