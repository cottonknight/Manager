<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>人员信息管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		function check(){
			var name = $('#uname').val();
			var pwd = $('#upwd').val();
			if(name == null || pwd == null || name.length==0 || pwd.length==0){
				alert("用户名密码不能为空！");
				return false;
			}
			return true;
		}
	</script>
	<c:if test="${ifrom=='register' }">
		<script type="text/javascript">
			alert("注册成功！请登录");
		</script>
	</c:if>
  </head>
  <body>
  <center><h2>登录</h2>
  	<form action="UserServlet?from=login" method="post" onsubmit="return check()">
  		<p>姓名：<input type="text" name="uname" id="uname" value="${u.name }"/></p>
  		<p>密码：<input type="password" name="upwd" id="upwd" value="${u.pwd }"/></p>
  		<p><input type="submit" value="登录"/>&nbsp;没有账号？点击<a href="register.jsp">注册</a></p>
  	</form>
  	</center>
  </body>
</html>
