package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.todo.model.MTodo;

@Mapper
public interface TodoMapper {
	
	/** ユーザー固有のTODOテーブルを作成 */
	public int createUserOwnTable(int userId);
	
	/** 作成された固有テーブルの存在を確認 */
	public int countUserOwnTable(int userId);
	
	/** Todoリスト取得 */
	public List<MTodo> findManyTodo(int loginUserId);
	
	/** Todo1件取得 */
	public MTodo findOneTodo(int id, int loginUserId);
	
	/** Todo1件登録 */
	public int insertOneTodo(MTodo todo, int loginUserId);
	
	/** Todo1件更新 */
	public int updateOneTodo(MTodo todo, int loginUserId);

}
