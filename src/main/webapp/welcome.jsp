<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改个人信息</title>
    
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
	<c:if test="${ifrom=='login' }">
		<script type="text/javascript">
			alert("登录成功！");
		</script>
	</c:if>
	<c:if test="${ifrom=='update' }">
		<script type="text/javascript">
			alert("修改成功！");
		</script>
	</c:if>
	
  </head>
  
  <body>
  	欢迎您:${user.name }&nbsp;&nbsp;&nbsp;当前时间：<span id="nowTime"> </span>
  	<center>
  		<form action="UserServlet?from=update" method="post" onsubmit="return check()">
  			<p>用户编号：<input type="text" value="${user.uid }" disabled="disabled"/></p>
  			<input type="hidden" name="uid" value="${user.uid }" />
  			<p>姓名：<input type="text" name="uname" id="uname" value="${user.name }"/></p>
  			<p>密码：<input type="text" name="upwd" id="upwd" value="${user.pwd }"/></p>
  			<p><input type="submit" value="修改"/>&nbsp;<a href="index.jsp">返回</a></p>
  		</form>
  	</center>
  </body>
   <script type="text/javascript">
 	var time = new Date();
  	document.getElementById('nowTime').innerHTML=time;
  </script>
</html>
