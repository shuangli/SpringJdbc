package com.xyz.dao;

import com.xyz.domain.User;

public interface UserDAO {

	public void insertUser(User user);
	public User selectUser(int userId);
	public void deleteInserted();
	
}