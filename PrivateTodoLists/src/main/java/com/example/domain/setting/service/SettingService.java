package com.example.domain.setting.service;

import java.util.Map;

import com.example.domain.setting.model.MSetting;
import com.example.form.SettingForm;

public interface SettingService {
	
//	/** Setting設定項目全件取得 **/
//	public List<MSetting> getSettingList();
	
	/** Setting設定項目全件取得 **/
	public Map<String,String> getSettingMap();
	
	/** Setting項目登録(1件毎) **/
	public void registerOneSetting(MSetting setting);
	
	/** Setting項目更新(1件毎) **/
	public void editOneSetting(MSetting setting);
	

	/** Setting登録・更新(一括) **/
	public void setAllSettings(SettingForm form) throws IllegalAccessException ;
	
	/** Setting登録・更新(1件毎) **/
	public void setOneSetting(String CustomizeKey, String CustomizeValue);
}
