package com.example.domain.tag.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.tag.model.MTag;
import com.example.domain.tag.service.TagService;
import com.example.repository.TagMapper;
import com.example.security.AuthUtil;

//@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {
	
	@Autowired
	private TagMapper tagMapper;
	
//	private int getLoginUserId() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		UserWithNameAndId userWithNameAndId = (UserWithNameAndId) authentication.getPrincipal();
//		return userWithNameAndId.getId();
//	}
	
	/** Tagリスト取得 */
	@Override
	public List<MTag> getTagItems() {
		return tagMapper.findManyTag(AuthUtil.getLoginUserId());
	}
	
	/** Tag1件取得 */
	@Override
	public MTag getOneTag(int id) {
		return tagMapper.findOneTag(id, AuthUtil.getLoginUserId());
	}
	
	/** Tag1件登録 */
	@Override
	public void createOneTag(MTag tag) {
		tag.setUserId(AuthUtil.getLoginUserId());
		tagMapper.insertOneTag(tag);
	}
	
	/** Tag1件更新 */
	@Override
	public void editOneTag(MTag tag) {
		tag.setUserId(AuthUtil.getLoginUserId());
		tagMapper.updateOneTag(tag);
	}

}
