package com.personal.project.user;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserSingleton {
	
	public static User getInstance() {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();   	
    	UserDetail userDetail = (UserDetail)principal;
    	User user = userDetail.getUser();
    	
    	return user;
	}

}
