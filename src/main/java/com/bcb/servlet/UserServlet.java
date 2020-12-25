package com.bcb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bcb.biz.UserBiz;
import com.bcb.biz.impl.UserBizImpl;
import com.bcb.dao.UserDao;
import com.bcb.dao.impl.UserDaoImpl;
import com.bcb.entity.User;

public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String from = request.getParameter("from");
		if("delete".equals(from)){
			delete(request,response,out);
		}else if("login".equals(from)){
			login(request,response,out);
			
		}else if("reg".equals(from)){
			reg(request,response,out);
			
		}else if("toManager".equals(from)){
			toManager(request,response,out);
			
		}else if("update".equals(from)){
			update(request,response,out);
			
		}

		out.flush();
		out.close();
	}

	private void update(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws ServletException, IOException {
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd");
		int uid = Integer.valueOf(request.getParameter("uid"));
		UserBiz biz = new UserBizImpl();
		User u = biz.update(new User(uid,name,pwd));
		if(u!=null) {
			request.setAttribute("ifrom", "update");
			request.setAttribute("user", u);
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		}else {
			out.print("<script>alert('修改失败！');history.go(-1);</script>");
		}
		
	}

	private void toManager(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws ServletException, IOException {
		UserBiz biz = new UserBizImpl();
		UserDao dao = new UserDaoImpl();
		int count = dao.getUserCount();	
		int size = 5;
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.valueOf(request.getParameter("page"));
		}
		List<User> list = biz.getUserByPage(page, size);
		int countpage = count%size==0?count/size:count/size+1;
		if(list.size() == 0) {
			out.print("<script>alert('获取列表失败！');location='index.jsp';</script>");
		}else {
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			request.setAttribute("countpage", countpage);
			request.setAttribute("from", "login");
			request.getRequestDispatcher("manager.jsp").forward(request, response);
			return;
		}
		
	}

	private void reg(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String yanzhengma =  (String) session.getAttribute("rand");
		String yanzhengma_input = request.getParameter("yanzheng");
		if(yanzhengma==null || yanzhengma_input==null) {
			out.print("<script>alert('非法注册！');location='index.jsp';</script>");
			return;
		}
		
		if(!yanzhengma.equals(yanzhengma_input)){
			out.print("<script>alert('验证码错误！');history.go(-1);</script>");
			return;
		}
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd");
		UserBiz biz = new UserBizImpl();
		User u = biz.insert(new User(name,pwd));
		if(u !=null) {
			request.setAttribute("u", u);
			request.setAttribute("ifrom", "register");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			out.print("<script>alert('用户名已被使用！';location='register.jsp';)</script>");
		}
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException, ServletException {
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd");
		String admin_name = getInitParameter("name");
		String admin_pwd = getInitParameter("pwd");	
		UserDao dao = new UserDaoImpl();
		if(name.equals(admin_name) && pwd.equals(admin_pwd)) {
			//管理员
			response.sendRedirect("UserServlet?from=toManager");;
		}else {
			User user = dao.CheckLogin(name, pwd);
			if(user != null) {
				// 普通用户
				request.setAttribute("ifrom", "login");
				request.setAttribute("user", user);
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			}else {
				// 登录失败
				out.print("<script>alert('用户名或密码错误！');location='index.jsp';</script>");
			}
		}
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		UserDao dao = new UserDaoImpl();
		dao.deleteById(id);
		request.getRequestDispatcher("UserServlet?from=toManager").forward(request, response);
		
	}

	

}
