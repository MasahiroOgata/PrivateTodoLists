package com.example.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoMapper {
	
	/** ユーザー固有のTODOテーブルを作成 */
	public int createUserOwnTable(int userId);
	
	/** 作成された固有テーブルの存在を確認 */
	public int countUserOwnTable(int userId);

}
