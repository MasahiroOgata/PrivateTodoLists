package com.example.aspect;

import java.io.File;
import java.util.Map;
import java.util.Random;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.domain.setting.service.SettingService;
import com.example.domain.todo.service.TodoService;

@Aspect
@Component
public class SettingAspect {
	
	@Autowired
	private SettingService settingService;
	
	@Autowired
	private TodoService todoService;
	
	private String getRandomImgURL() {
		File dir = new File("src/main/resources/static/img");
		String[] imgList = dir.list();		
		Random random = new Random();
		int imgNum = random.nextInt(imgList.length);
		return imgList[imgNum]; 
	}
	
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
					
					if ("random".equals(settingMap.get("backgroundImg"))) {
						settingMap.put("imgURL", getRandomImgURL());
					} else {
						settingMap.put("imgURL", settingMap.getOrDefault("backgroundImg", ""));
					}
					
//					System.out.println(settingMap);
				
				
					model.addAttribute("settingMap", settingMap);
					
					model.addAttribute("unfinishedTodoCount", todoService.getUnfinishedTodoCount());
				}
			}
        }
	}
	
	@Before("bean(loginController) || bean(signupController)")
	public void getBackgroundImage(JoinPoint joinPoint) {
		
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			if (arg instanceof Model) {
				
				Model model = (Model) arg;
//				File dir = new File("src/main/resources/static/img");
//				String[] imgList = dir.list();		
//				Random random = new Random();
//				int imgNum = random.nextInt(imgList.length);
//				String imgURL = imgList[imgNum]; 
				model.addAttribute("imgURL", getRandomImgURL());			
			}
		}
		
	}

}
