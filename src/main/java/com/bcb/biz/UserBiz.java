package com.bcb.biz;

import java.util.List;

import com.bcb.entity.User;

public interface UserBiz {
	public User update(User u);
	public List<User> getUserByPage(int page,int size);
	public User insert(User u);
}
