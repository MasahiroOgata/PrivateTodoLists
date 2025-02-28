package com.example.domain.todo.service;

public interface TodoService {
	
	/** ユーザー固有のTODOテーブルを作成 */
	public void makeUserOwnTable(int userId);
	
//	/** ユーザー登録および固有のTODOテーブルを作成 */
//	public void signupUserAndCreateOwnTable2(MUser user);

}
