package com.example.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.user.model.MUser;

@Mapper
public interface UserMapper {
	
	/** ユーザー登録 */
	public int insertOneUser(MUser user);
	
	/** ログインユーザー情報取得、およびユーザーID重複確認 */
	public MUser findUserByUserId(String userId);

}
