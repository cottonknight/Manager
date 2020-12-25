package com.bcb.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bcb.dao.UserDao;
import com.bcb.entity.User;
import com.bcb.util.JDBCUtil;

public class UserDaoImpl implements UserDao{
	JDBCUtil con = JDBCUtil.getUtil();
	@Override
	public User CheckLogin(String name, String pwd) {
		String sql = "select * from t_luser where tname=? and tpwd=?";
		ResultSet rs = con.query(sql, name,pwd);
		User user = null;
		try {
			if(rs.next()) {
				user = new User(rs.getInt("t_id"),name,pwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.closeAll();
		}
		return user;
	}
	@Override
	public User update(User u) {
		boolean flag = con.update("update t_luser set tname=?,tpwd=? "
				+ "where t_id=?", u.getName(),u.getPwd(),u.getUid())>0;	
		if(flag) {
			return getUserById(u.getUid());
		}else {
			return null;
		}		
		
	}
	@Override
	public User getUserById(int id) {
		String sql = "select * from t_luser where t_id=?";
		ResultSet rs = con.query(sql,id);
		User user = null;
		try {
			if(rs.next()) {
				user = new User(rs.getInt("t_id"),rs.getString("tname"),rs.getString("tpwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.closeAll();
		}
		return user;
	}
	@Override
	public User insert(User u) {
		String sql = "insert into t_luser values(?,?,?)";
		if(con.update(sql, u.getUid(),u.getName(),u.getPwd())>0) {
			return CheckLogin(u.getName(), u.getPwd());
		}	
		// 插入用户和查询最大id应该和在一起成功或失败，后期用框架实现
		return null;
	}
	@Override
	public int getUserCount() {
		int count = 0;
		ResultSet rs = con.query("select count(*) num from t_luser");
		try {
			if(rs.next()) {
				count = rs.getInt("num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			con.closeAll();
		}
		return count;
	}
	@Override
	public List<User> getUserByPage(int page, int size) {
		String sql= "select s.* from(select t.*,rownum snum "
				+ "from(select * from t_luser order by t_id) t "
				+ "where rownum<="+(page*size)+") s where s.snum>"+(page-1)*size;
		List<User> list = new ArrayList<User>();
		ResultSet rs = con.query(sql);
		try {
			while(rs.next()) {
				User u= new User(rs.getInt("t_id"),rs.getString("tname"),rs.getString("tpwd"));
				list.add(u);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			con.closeAll();
		}
		return list;
	}
	@Override
	public boolean deleteById(int id) {		
		return con.update("delete t_luser where t_id=?", id) > 0;
	}

}
