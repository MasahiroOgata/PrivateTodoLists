package com.example.domain.todo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.todo.model.MTodo;
import com.example.domain.todo.service.TodoService;
import com.example.domain.user.service.impl.UserWithNameAndId;
import com.example.repository.TodoMapper;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoMapper todoMapper;
	
	private int getLoginUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserWithNameAndId userWithNameAndId = (UserWithNameAndId) authentication.getPrincipal();
		return userWithNameAndId.getId();
	}
	
	/** ユーザー固有のTODOテーブルを作成 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void makeUserOwnTable(String signupUserId) {
		todoMapper.createUserOwnTable(signupUserId);
	}
	
	/** 作成された固有テーブルの存在を確認 */
	@Override
	public boolean existsUserOwnTable(String signupUserId) {
		return todoMapper.countUserOwnTable(signupUserId) == 1;
	}
	
	/** Todoリスト取得 */
	@Override
	public List<MTodo> getTodoItems() {
		return todoMapper.findManyTodo(getLoginUserId());
	}
	
	/** Todo1件取得 */
	@Override
	public MTodo getOneTodo(int id) {
		return todoMapper.findOneTodo(id, getLoginUserId());
	}
	
	/** Todo1件登録 */
	@Override
	public void createOneTodo(MTodo todo) {
		todoMapper.insertOneTodo(todo, getLoginUserId());
	}
	
	/** Todo1件更新 */
	@Override
	public void editOneTodo(MTodo todo) {
		todoMapper.updateOneTodo(todo, getLoginUserId());
	}


}
