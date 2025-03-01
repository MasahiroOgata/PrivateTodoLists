package com.example.domain.todo.service;

public interface TodoService {
	
	/** ユーザー固有のTODOテーブルを作成 */
	public void makeUserOwnTable(int userId);

	/** 作成された固有テーブルの存在を確認 */
	public boolean existsUserOwnTable(int userId);

}
