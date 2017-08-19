<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'formIntroduceAction4.jsp' starting page</title>
    

  </head>
  
  <body>
  
  	<h3>演示Servlet的API的第一种方式（完全解耦合的方式）</h3>

<form action="${ pageContext.request.contextPath }/introduce_fun1.action" method="post">
	姓名:<input type="text" name="username" /><br/>
	密码:<input type="password" name="password" /><br/>
	<input type="submit" value="注册" />
</form>


<h3>使用的是ServletActionContext类方法</h3>

<form action="${ pageContext.request.contextPath }/introduce_fun2.action" method="post">
	姓名:<input type="text" name="username" /><br/>
	密码:<input type="password" name="password" /><br/>
	<input type="submit" value="注册" />
</form>
  </body>
</html>
