package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.setting.model.MSetting;

@Mapper
public interface SettingMapper {
	
	/** Setting設定項目全件取得 **/
	public List<MSetting> findAllSettings(int userId);

}
