package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.todo.model.MTodo;

@Mapper
public interface TodoMapper {
	
	/** ユーザー固有のTODOテーブルを作成 */
	public int createUserOwnTable(String signupUserId);
	
	/** 作成された固有テーブルの存在を確認 */
	public int countUserOwnTable(String signupUserId);
	
	/** Todoリスト取得 */
	public List<MTodo> findManyTodo(String loginUserId);
	
	/** Todo1件取得 */
	public MTodo findOneTodo(int id, String loginUserId);
	
	/** Todo1件登録 */
	public int insertOneTodo(MTodo todo, String loginUserId);
	
	/** Todo1件更新 */
	public int updateOneTodo(MTodo todo, String loginUserId);
	
	/** Todo1件削除 */
	public int setOneTodoDeleted(int id, String loginUserId);

}
