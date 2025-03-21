package com.example.domain.user.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	
	/** ユーザー登録 */
//	@Transactional
	@Override	
	public void signupUser(MUser user) {
		String rawPassword = user.getPassword();
		user.setPassword(passwordEncoder.encode(rawPassword));
		userMapper.insertOneUser(user);
//		String signupUserId = String.valueOf(user.getId());
//		todoService.makeUserOwnTable(signupUserId);
//		if (!todoService.existsUserOwnTable(signupUserId)) {
//			throw new RuntimeException();
//		}
	}
	
	/** ユーザーID重複確認 */
	@Override
	public boolean isRegisterdUserId(String userId) {
		return Objects.nonNull(userMapper.findUserByUserId(userId));
	}
	
	/** ログインユーザー情報取得 */
	@Override
	public MUser getLoginUser(String userId) {
		return userMapper.findUserByUserId(userId);
	}

}
