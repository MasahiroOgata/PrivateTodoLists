package com.example.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.tag.model.MTag;

@Mapper
public interface TagMapper {
	
	/** Tag1件登録 */
	public int insertOneTag(MTag tag);

}
