package com.example.domain.tag.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.domain.tag.model.MTag;
import com.example.domain.tag.service.TagService;
import com.example.domain.user.service.impl.UserWithNameAndId;
import com.example.repository.TagMapper;

@Service
public class TagServiceImpl implements TagService {
	
	@Autowired
	private TagMapper tagMapper;
	
	private int getLoginUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserWithNameAndId userWithNameAndId = (UserWithNameAndId) authentication.getPrincipal();
		return userWithNameAndId.getId();
	}
	
	/** Tag1件登録 */
	public void createOneTag(MTag tag) {
		tag.setUserId(getLoginUserId());
		tagMapper.insertOneTag(tag);
	}

}
