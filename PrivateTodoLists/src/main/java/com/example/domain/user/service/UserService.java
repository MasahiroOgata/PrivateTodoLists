package com.example.domain.user.service;

import com.example.domain.user.model.MUser;

public interface UserService {
	
	/** ユーザー登録 */
	public void signup(MUser user);
	
	/** ユーザー登録および固有のTODOテーブルを作成 */
	public void signupUserAndCreateOwnTable(MUser user);

}
