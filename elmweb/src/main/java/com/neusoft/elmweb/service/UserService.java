package com.neusoft.elmweb.service;

import com.neusoft.elmweb.po.User;

public interface UserService {

	public User getUserByIdByPass(String userid, String password);

	public int saveUser(User user);

	public User getUserById(String userId);

}
