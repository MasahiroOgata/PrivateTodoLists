package com.example.domain.user.service;

import com.example.domain.user.model.MUser;

public interface UserService {
	
	/** ユーザー登録、および固有のTODOテーブルの作成・確認 */
	public void signupUserAndCreateOwnTable(MUser user);

}
