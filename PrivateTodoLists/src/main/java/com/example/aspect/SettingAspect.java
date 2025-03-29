package com.example.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class SettingAspect {
	
	@Before("bean(Todo*Controller)")
	public void applySetting () {
		
		//if(SecurityContextHolder.getContext().getAuthentication() != null){ 
			log.info(SecurityContextHolder.getContext().getAuthentication().toString());
			log.info("メソッド開始");
		//}
	}

}
