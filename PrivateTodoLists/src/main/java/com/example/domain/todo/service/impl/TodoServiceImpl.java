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
	
//	@Autowired
//	private UserService userService;
	
	/** ユーザー固有のTODOテーブルを作成 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void makeUserOwnTable(int userId) {
		todoMapper.createUserOwnTable(userId);
		//throw new RuntimeException();
	}
	
//	/** ユーザー登録および固有のTODOテーブルを作成 */
//	@Override
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
//	public void signupUserAndCreateOwnTable2(MUser user) {
//		userService.signup(user);
//		todoMapper.createUserOwnTable(user.getId());
//		throw new RuntimeException();
//	}

}
