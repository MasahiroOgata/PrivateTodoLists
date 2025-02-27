package com.example.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoMapper {
	
	/** ユーザー固有のTODOテーブルを作成 */
	public int createUserOwnTable(int userId);

}
