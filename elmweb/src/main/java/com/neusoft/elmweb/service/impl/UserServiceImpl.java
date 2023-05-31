package com.neusoft.elmweb.service.impl;

import com.neusoft.elmweb.dao.UserDao;
import com.neusoft.elmweb.dao.impl.UserDaoImpl;
import com.neusoft.elmweb.po.User;
import com.neusoft.elmweb.service.UserService;

public class UserServiceImpl implements UserService {
	
	UserDao dao = new UserDaoImpl();

	@Override
	public User getUserByIdByPass(String userid, String password) {

		return dao.getUserByIdByPass(userid,password);
	}

	@Override
	public int saveUser(User user) {
		
		return dao.saveUser(user);
	}

	@Override
	public User getUserById(String userId) {
		
		return dao.getUserById(userId);
	}

}
