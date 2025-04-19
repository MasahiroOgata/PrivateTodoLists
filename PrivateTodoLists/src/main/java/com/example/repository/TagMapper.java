package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.tag.model.MTag;

@Mapper
public interface TagMapper {
	
	/** Tagリスト取得 */
	public List<MTag> findManyTag(int userId);
	
	/** Tag1件取得 */
	public MTag findOneTag(int id, int userId);
	
	/** Tag1件登録 */
	public int insertOneTag(MTag tag);
	
	/** Tag1件更新 */
	public int updateOneTag(MTag tag);

}
