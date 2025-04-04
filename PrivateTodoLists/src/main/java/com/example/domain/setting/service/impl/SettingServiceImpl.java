package com.example.domain.setting.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.domain.setting.model.MSetting;
import com.example.domain.setting.service.SettingService;
import com.example.domain.user.service.impl.UserWithNameAndId;
import com.example.form.SettingForm;
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
	
//	/** Setting設定項目全件取得 **/
//	@Override
//	public List<MSetting> getSettingList() {
//		return settingMapper.findAllSettings(getLoginUserId());
//	}
	
	/** Setting項目全件取得 **/
	@Override
	public Map<String, String> getSettingMap() {
		List<MSetting> settingList = settingMapper.findAllSettings(getLoginUserId());
		Map<String, String> settingMap = new HashMap<>();
		settingList.forEach(s -> settingMap.put(s.getCustomizeKey(), s.getCustomizeValue()));
		return settingMap;
	}
	
	/** Setting項目登録(1件毎) **/
	@Override
	public void registerOneSetting(MSetting setting) {
		setting.setUserId(getLoginUserId());
		settingMapper.insertOneSetting(setting);
	}
		
	/** Setting項目更新(1件毎) **/
	@Override
	public void editOneSetting(MSetting setting) {
		setting.setUserId(getLoginUserId());
		settingMapper.updateOneSetting(setting);
	}
	
	/** Setting登録・更新(一括) **/
	@Override
	public void setAllSettings(SettingForm form) throws IllegalAccessException  {
		
		Map<String, String> settingMap = getSettingMap();
		 
		for(Field field : form.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			MSetting setting = new MSetting();			
			setting.setCustomizeKey(field.getName());
			setting.setCustomizeValue(field.get(form).toString());
			
			if (settingMap.containsKey(setting.getCustomizeKey())) {
				editOneSetting(setting);
			} else {
				registerOneSetting(setting);
			}
		}
	}

}
