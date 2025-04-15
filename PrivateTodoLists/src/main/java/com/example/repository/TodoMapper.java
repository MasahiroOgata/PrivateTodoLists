package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.todo.model.MTodo;

@Mapper
public interface TodoMapper {
	
//	/** ユーザー固有のTODOテーブルを作成 */
//	public int createUserOwnTable(String signupUserId);
//	
//	/** 作成された固有テーブルの存在を確認 */
//	public int countUserOwnTable(String signupUserId);
	
	/** Todoリスト取得 */
	public List<MTodo> findManyTodo(@Param("search") String search, int userId);
	
	/** Todo1件取得 */
	public MTodo findOneTodo(int id, int userId);
	
	/** Todo1件登録 */
	public int insertOneTodo(MTodo todo);
	
	/** Todo1件更新 */
	public int updateOneTodo(MTodo todo);
	
	/** Todo1件削除 */
	public int setOneTodoDeleted(int id, int userId);
	
	/** 未完了Todo件数取得 */
	public int countUnfinishedTodo(int userId);

}
