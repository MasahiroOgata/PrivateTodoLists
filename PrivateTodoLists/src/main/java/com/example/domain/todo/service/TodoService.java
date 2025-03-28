package com.example.domain.todo.service;

import java.util.List;

import com.example.domain.todo.model.MTodo;

public interface TodoService {
	
//	/** ユーザー固有のTODOテーブルを作成 */
//	public void makeUserOwnTable(String signupUserId);
//
//	/** 作成された固有テーブルの存在を確認 */
//	public boolean existsUserOwnTable(String signupUserId);
	
	/** Todoリスト取得 */
	public List<MTodo> getTodoItems(String search);
	
	/** Todo1件取得 */
	public MTodo getOneTodo(int id);
	
	/** Todo1件登録 */
	public void createOneTodo(MTodo todo);
	
	/** Todo1件更新 */
	public void editOneTodo(MTodo todo);
	
	/** Todo1件削除 */
	public void deleteOneTodo(int id);

}
