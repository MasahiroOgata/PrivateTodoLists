package com.example.aspect;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.domain.setting.service.SettingService;

@Aspect
@Component
public class SettingAspect {
	
	@Autowired
	SettingService settingService;
	
	@Before("bean(*Controller) && !bean(loginController) && !bean(signupController)")			
	public void applySetting (JoinPoint joinPoint) {
	
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if(signature.getMethod().isAnnotationPresent(GetMapping.class)) {
        	Object[] args = joinPoint.getArgs();
			for (Object arg : args) {
//				if (arg instanceof HttpServletRequest) {
//				HttpServletRequest request = (HttpServletRequest) arg;
//				request.setAttribute("settingMap", settingMap);
				if (arg instanceof Model) {
					Model model = (Model) arg;
					
					Map<String, String> settingMap = settingService.getSettingMap();
					model.addAttribute("settingMap", settingMap);
				}
			}
        }
	}

}
