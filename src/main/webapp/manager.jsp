<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/MyPage.tld" prefix="pt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	<c:if test="${from=='login' }">
		<script type="text/javascript">
			alert("登录成功！");
		</script>
	</c:if>
	<c:if test="${from=='delete' }">
		<script type="text/javascript">
			alert("删除成功！");
		</script>
	</c:if>
	-->
	
  </head>
  <body>
  	<a href="index.jsp">退出</a>
  <center>
  	<h2>管理员页面</h2>
  	<table>
  	<tr>
  		<td>编号：</td>
  		<td>姓名：</td>
  		<td>密码：</td>
  		<td>删除</td>
  	</tr>
  	<c:forEach  items="${list }" var="u" varStatus="s" >
  		<tr <c:if test="${s.index%2==1 }">style="background-color: gray"</c:if>>	
  			<td>${u.uid }</td>
  			<td>${u.name }</td>
  			<td>${u.pwd }</td>
  			<td><a href="UserServlet?id=${u.uid }&page=${page}&from=delete">删除</a></td>
  		</tr>
  	</c:forEach>
  	</table>
  	<pt:page pageIndex="${page }" url="UserServlet?from=toManager&" pageMax="${countpage }"/>
  </center>
  </body>
</html>
