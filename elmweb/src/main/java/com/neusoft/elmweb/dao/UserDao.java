package com.neusoft.elmweb.dao;

import com.neusoft.elmweb.po.User;

public interface UserDao {

	public User getUserByIdByPass(String userid, String password);

	public int saveUser(User user);

	public User getUserById(String userId);

}
