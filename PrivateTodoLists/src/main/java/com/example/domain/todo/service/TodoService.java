package com.example.domain.todo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.domain.todo.model.MTodo;

public interface TodoService {
	
//	/** ユーザー固有のTODOテーブルを作成 */
//	public void makeUserOwnTable(String signupUserId);
//
//	/** 作成された固有テーブルの存在を確認 */
//	public boolean existsUserOwnTable(String signupUserId);
	
	/** Todoリスト取得 */
	public List<MTodo> getTodoItems(String search);
	
	/** Todoリスト取得（ページネーション付き） */
	public Page<MTodo> getTodoItemsWithPages(String search, Pageable pageable);
	
	/** Todo1件取得 */
	public MTodo getOneTodo(int id);
	
	/** Todo1件登録 */
	public void createOneTodo(MTodo todo);
	
	/** Todo1件更新 */
	public void editOneTodo(MTodo todo);
	
	/** Todo1件削除 */
	public void deleteOneTodo(int id);
	
	/** 未完了Todo件数取得 */
	public int getUnfinishedTodoCount();
	
	/** 本日期限の未完了Todo件数取得 **/
	public int getTodayExpiringTodoCount();

}
