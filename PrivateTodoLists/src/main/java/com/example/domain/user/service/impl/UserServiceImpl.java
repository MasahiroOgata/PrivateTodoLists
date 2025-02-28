package com.example.domain.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.todo.service.TodoService;
import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private TodoService todoService;
	
	
	/** ユーザー登録 */
	@Transactional//(propagation = Propagation.REQUIRES_NEW)
	@Override	
	public void signup(MUser user) {
		userMapper.insertOneUser(user);
	}
	
	/** ユーザー登録および固有のTODOテーブルを作成 */
	@Transactional
	@Override	
	public void signupUserAndCreateOwnTable(MUser user) {
		userMapper.insertOneUser(user);
		todoService.makeUserOwnTable(user.getId());
		//throw new RuntimeException();
		//int i = 1 / 0;
	}

}
