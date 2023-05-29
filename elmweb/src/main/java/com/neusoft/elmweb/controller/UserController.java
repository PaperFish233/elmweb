package com.neusoft.elmweb.controller;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elmweb.po.User;
import com.neusoft.elmweb.service.UserService;
import com.neusoft.elmweb.service.impl.UserServiceImpl;

public class UserController {
	
	UserService service = new UserServiceImpl();

	public Object getUserByIdByPass(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		User user = service.getUserByIdByPass(userid,password);
		return user;
	}
	
	public Object saveUser(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        Integer userSex = Integer.valueOf(request.getParameter("userSex"));
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setUserName(userName);
        user.setUserSex(userSex);   
        user.setUserImg(null);
        UserService service = new UserServiceImpl();
        int result = service.saveUser(user);
        return result;
    }
	
	public Object getUserById(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        UserService service = new UserServiceImpl();
        User user = service.getUserById(userId);
        return user;
    }

}
