package com.example.domain.user.service;

import com.example.domain.user.model.MUser;

public interface UserService {
	
	/** ユーザー登録 */
	public void signupUser(MUser user);
	
	/** ユーザーID重複確認 */
	public boolean isRegisterdUserId(String userId);
	
	/** ログインユーザー情報取得 */
	public MUser getLoginUser(String userId);

}
