package com.example.domain.todo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.todo.model.MTodo;
import com.example.domain.todo.service.TodoService;
import com.example.repository.TodoMapper;
import com.example.security.AuthUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
	
	private final TodoMapper todoMapper;
	
//	private int getLoginUserId() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		UserWithNameAndId userWithNameAndId = (UserWithNameAndId) authentication.getPrincipal();
//		return userWithNameAndId.getId();
//	}
	
//	/** ユーザー固有のTODOテーブルを作成 */
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
//	@Override
//	public void makeUserOwnTable(String signupUserId) {
//		todoMapper.createUserOwnTable(signupUserId);
//	}
//	
//	/** 作成された固有テーブルの存在を確認 */
//	@Override
//	public boolean existsUserOwnTable(String signupUserId) {
//		return todoMapper.countUserOwnTable(signupUserId) == 1;
//	}
	
	/** Todoリスト取得 */
	@Override
	public List<MTodo> getTodoItems(String search) {
		//RowBounds rowBounds = new RowBounds((int)pageable.getOffset(), pageable.getPageSize());
		return todoMapper.findManyTodo(search, AuthUtil.getLoginUserId());
	}
	
	/** Todo1件取得 */
	@Override
	public MTodo getOneTodo(int id) {
		return todoMapper.findOneTodo(id, AuthUtil.getLoginUserId());
	}
	
	/** Todo1件登録 */
	@Override
	public void createOneTodo(MTodo todo) {
		todo.setUserId(AuthUtil.getLoginUserId());
		todoMapper.insertOneTodo(todo);
	}
	
	/** Todo1件更新 */
	@Override
	public void editOneTodo(MTodo todo) {
		todo.setUserId(AuthUtil.getLoginUserId());
		todoMapper.updateOneTodo(todo);
	}
	
	/** Todo1件削除 */
	public void deleteOneTodo(int id) {
		todoMapper.setOneTodoDeleted(id, AuthUtil.getLoginUserId());
	}
	
	/** 未完了Todo件数取得 */
	@Override
	public int getUnfinishedTodoCount() {
		return todoMapper.countUnfinishedTodo(AuthUtil.getLoginUserId());
	}






}
