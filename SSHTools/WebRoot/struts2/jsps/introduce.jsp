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
    
    <title>My JSP 'introduce.jsp' starting page</title>
    
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
 	<!-- 演示最基础的POJO访问 -->
    <a href="<c:url value = '/sayHello'/>">演示Introduceaction1</a><br>
  	<!-- 访问方式，对应struts.xml -->
  	<h1>传统的配置文件方式</h1>
  	<a href = "${pageContext.request.contextPath }/save.action">保存方法</a>
  	<a href = "${pageContext.request.contextPath }/delete.action">删除方法</a>
  	<h1>通配符的方式(应用比较多)</h1>
  	<a href = "${pageContext.request.contextPath }/linkman_save.action">保存方法</a>
  	<a href = "${pageContext.request.contextPath }/linkman_delete.action">删除通配符方法</a>
  	<h1>动态方法访问的方式</h1>
  	<a href = "${pageContext.request.contextPath }/user!save.action">保存利用动态方法访问</a>
 	<a href = "${pageContext.request.contextPath }/user!delete.action">删除</a>
  </body>
</html>
