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
    
    <title>My JSP 'IntroduceActionForm.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    
<h3>属性驱动的方式</h3>

<form action="${ pageContext.request.contextPath }/introduceformParameter_fun1.action" method="post">
	姓名:<input type="text" name="username" /><br/>
	密码:<input type="password" name="password" /><br/>
	年龄:<input type="password" name="age" /><br/>
	<input type="submit" value="注册" />
</form>

<h3>属性驱动的方式（把数据封装到JavaBean的对象中）</h3>
<!-- 注意一：页面的编写规则，发生了变化，使用的OGNL表达式的写法 -->
<form action="${ pageContext.request.contextPath }/introduceformParameter_fun2.action" method="post">
	姓名:<input type="text" name="user2.username" /><br/>
	密码:<input type="password" name="user2.password" /><br/>
	年龄:<input type="password" name="user2.age" /><br/>
	<input type="submit" value="注册" />
</form>


<h3>模型驱动方式</h3>
<form action="${ pageContext.request.contextPath }/introduceformParameter_fun3.action" method="post">
	姓名:<input type="text" name="username" /><br/>
	密码:<input type="password" name="password" /><br/>
	年龄:<input type="password" name="age" /><br/>
	<input type="submit" value="注册" />
</form>

<h3>向List集合封装数据（默认情况下，采用是属性驱动的方式）</h3>
<!-- 后台：List<User> list -->
<form action="${ pageContext.request.contextPath }/introduceformParameter_fun4.action" method="post">
	姓名:<input type="text" name="list[0].username" /><br/>
	密码:<input type="password" name="list[0].password" /><br/>
	年龄:<input type="password" name="list[0].age" /><br/>
	
	姓名:<input type="text" name="list[1].username" /><br/>
	密码:<input type="password" name="list[1].password" /><br/>
	年龄:<input type="password" name="list[1].age" /><br/>
	<input type="submit" value="注册" />
</form>

<h3>向Map集合封装数据（默认情况下，采用是属性驱动的方式）</h3>
<form action="${ pageContext.request.contextPath }/introduceformParameter_fun5.action" method="post">
	姓名:<input type="text" name="map['one'].username" /><br/>
	密码:<input type="password" name="map['one'].password" /><br/>
	年龄:<input type="password" name="map['one'].age" /><br/>
	
	姓名:<input type="text" name="map['two'].username" /><br/>
	密码:<input type="password" name="map['two'].password" /><br/>
	年龄:<input type="password" name="map['two'].age" /><br/>
	<input type="submit" value="注册" />
</form>
  </body>
</html>
