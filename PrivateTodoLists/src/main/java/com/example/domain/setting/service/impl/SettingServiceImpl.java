package com.example.domain.setting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.domain.setting.model.MSetting;
import com.example.domain.setting.service.SettingService;
import com.example.domain.user.service.impl.UserWithNameAndId;
import com.example.repository.SettingMapper;

@Service
public class SettingServiceImpl implements SettingService{
	
	@Autowired
	private SettingMapper settingMapper;
	
	private int getLoginUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserWithNameAndId userWithNameAndId = (UserWithNameAndId) authentication.getPrincipal();
		return userWithNameAndId.getId();
	}
	
	/** Setting設定項目全件取得 **/
	public List<MSetting> getSettingList() {
		return settingMapper.findAllSettings(getLoginUserId());
	}

}
