package com.example.domain.setting.service;

import java.util.List;

import com.example.domain.setting.model.MSetting;

public interface SettingService {
	
	/** Setting設定項目全件取得 **/
	public List<MSetting> getSettingList();

}
