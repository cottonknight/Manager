<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    
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
  </head>
  
  <body>
  <center>
  	<h2>用户注册</h2>
  	<form action="UserServlet?from=reg" method="post" onsubmit="return check()">
  		<p>姓名：<input type="text" name="uname" id="uname"/></p>
  		<p>密码：<input type="password" name="upwd" id="upwd"/></p>
  		<p>
  			验证码：<input type="text" name="yanzheng" style="width: 80px"/>
  			<img alt="" src="image.jsp" onclick="src='image.jsp?'+Math.random();">
  		</p>
  		<p><input type="submit" value="注册"/>
  	</form>
  </center>
  </body>
</html>
