package com.example.domain.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/** ユーザー登録、および固有のTODOテーブルの作成・確認 */
	@Transactional
	@Override	
	public void signupUserAndCreateOwnTable(MUser user) {
		String rawPassword = user.getPassword();
		user.setPassword(passwordEncoder.encode(rawPassword));
		userMapper.insertOneUser(user);
		todoService.makeUserOwnTable(user.getId());
		if (!todoService.existsUserOwnTable(user.getId())) {
			throw new RuntimeException();
		}
	}
	
	/** ログインユーザー情報取得 */
	@Override
	public MUser getLoginUser(String userId) {
		return userMapper.findLoginUser(userId);
	}

}
