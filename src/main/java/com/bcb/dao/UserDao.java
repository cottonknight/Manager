package com.bcb.dao;

import java.util.List;

import com.bcb.entity.User;

public interface UserDao {
	public User CheckLogin(String name,String pwd);
	public User update(User u);
	public User getUserById(int id);
	public int getUserCount();
	public List<User> getUserByPage(int page,int size);
	public boolean deleteById(int id);
	public User insert(User u);
}
