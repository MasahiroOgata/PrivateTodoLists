package com.example.aspect;

import java.io.File;
import java.util.Map;
import java.util.Random;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.domain.setting.service.SettingService;
import com.example.domain.todo.service.TodoService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class SettingAspect {
	
	private final SettingService settingService;
	
	private final TodoService todoService;
	
	private final HttpServletRequest request;
	
	private String getRandomImgURL() {
		File dir = new File("src/main/resources/static/img");
		String[] imgList = dir.list();
		Random random = new Random();
		int imgNum = random.nextInt(imgList.length);
		return imgList[imgNum]; 
	}
	
	@Before("bean(*Controller) && !bean(loginController) && !bean(signupController)")			
	public void applySetting (JoinPoint joinPoint) {
		
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth == null || !auth.isAuthenticated() || auth.getPrincipal() instanceof String) {
	        return;
	    }
		
		Map<String, String> settingMap = settingService.getSettingMap();	
		
		if ("random".equals(settingMap.get("backgroundImg"))) {
			settingMap.put("imgURL", getRandomImgURL());
		} else {
			settingMap.put("imgURL", settingMap.getOrDefault("backgroundImg", "none"));
		}
		
		if (!settingMap.containsKey("isHidingFinishedTodo")) {
			settingMap.put("isHidingFinishedTodo", "0");
		}
		
		request.setAttribute("settingMap", settingMap);
		request.setAttribute("unfinishedTodoCount", todoService.getUnfinishedTodoCount());
		
	}
	
//	@Before("bean(loginController) ||bean(signupController)")
//	public void getBackgroundImage(JoinPoint joinPoint) {
//		
//		Object[] args = joinPoint.getArgs();
//		for (Object arg : args) {
//			if (arg instanceof Model) {				
//				Model model = (Model) arg;
//				model.addAttribute("imgURL", getRandomImgURL());			
//			}
//		}
//		
//	}

}
