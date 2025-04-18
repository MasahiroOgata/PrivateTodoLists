package com.example.domain.tag.service;

import java.util.List;

import com.example.domain.tag.model.MTag;

public interface TagService {
	
	/** Tagリスト取得 */
	public List<MTag> getTagItems();
	
	/** Tag1件登録 */
	public void createOneTag(MTag tag);

}
