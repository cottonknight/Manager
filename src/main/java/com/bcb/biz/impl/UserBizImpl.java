package com.bcb.biz.impl;

import java.util.List;

import com.bcb.biz.UserBiz;
import com.bcb.dao.UserDao;
import com.bcb.dao.impl.UserDaoImpl;
import com.bcb.entity.User;

public class UserBizImpl implements UserBiz{
	UserDao dao = new UserDaoImpl();

	@Override
	public User update(User u) {
		if(!u.getName().equals("admin")) {
			return dao.update(u);
		}
		return null;
	}

	@Override
	public User insert(User u) {
		if(!u.getName().equals("admin")) {
			return dao.insert(u);
		}
		
		return null;
	}

	@Override
	public List<User> getUserByPage(int page, int size) {
		int count = dao.getUserCount();
		int countpage = count%size==0?count/size:count/size+1;
		page = page>countpage?countpage:page;
		page = page<1?1:page;
		return dao.getUserByPage(page, size);
	}


}
