<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 引入Struts2标签库 -->    
<%@ taglib prefix="s" uri="/struts-tags"%>    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ognlsuccess.jsp' starting page</title>
    
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
  
<h3>条件：假如值栈中已经存入值了，在JSP页面上从值栈中获取值</h3>

<!--  
	1. 先引入Struts2框架提供的标签库，s标签
	2. 可以使用提供的标签（很多，掌握重点的标签）
-->

<!-- 从值栈中获取值的 value中间编写就是OGNL表达式 -->
<s:property value="'username'.length()"/><br/>

<h3>------------------------------------------------------</h3>
<h3>从值栈中获取值</h3>
<%-- //vs.push("david")
//获取到栈顶的值
<s:property value="[0].top"/> --%>


<%-- 	// 栈顶是map集合，通过key获取值
	vs.set("msg", "小凤");
	<s:property value="[0].top.msg"/> --%>


<!--  
	vs.push(user);
	// 栈顶放user对象
	<s:property value="[0].top.username"/>
	<s:property value="[0].top.password"/>
	// [0].top 关键字是可以省略的  findValue()
	<s:property value="username"/>
-->

<!--
	vs.set("user", user);
	<s:property value="[0].top.user.username"/>
	<s:property value="[0].top.user.password"/>
	// 省略关键字
	<s:property value="user.username"/>
-->

<!--  
	// 在ValueStack1Action提供了成员的属性
	private User user = new User("小泽","456");
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	User user = new User("小苍","123");
	vs.set("user", user);
	// 从栈顶开始查找，找user的属性，显示名称	返回的小苍
	<s:property value="user.username"/>
	
	// [1].top获取ValueStack1Action [1].top.user返回user对象  [1].top.user.username获取对象的属性名称
	<s:property value="[1].top.user.username"/>
-->

<!--  
	栈顶是list集合
	vs.push(ulist);
	<s:property value="[0].top[0].username"/>
	<s:property value="[0].top[1].username"/>
-->

<!--
	vs.set("ulist", ulist);
	<s:property value="ulist[0].username"/>
-->

<!-- 迭代的标签 
	属性
		* value	要迭代的集合，需要从值栈中获取
		* var	迭代过程中，遍历的对象
			* var编写上，把迭代产生的对象默认压入到context栈中，从context栈取值，加#号
			* var不编写，默认把迭代产生的对象压入到root栈中
		
	for(User user:ulist){}	
	// 编写var的属性
	<s:iterator value="ulist" var="u">
		<s:property value="#u.username"/>
		<s:property value="#u.password"/>
	</s:iterator>
	
	// 没有编写var关键字
	<s:iterator value="ulist">
		<s:property value="username"/>
		<s:property value="password"/>
	</s:iterator>
-->

<!-- 从context栈中获取值，加#号 

HttpServletRequest request = ServletActionContext.getRequest();
request.setAttribute("msg", "美美");
request.getSession().setAttribute("msg", "小风");

<s:property value="#request.msg"/>
<s:property value="#session.msg"/>
<s:property value="#parameters.id"/>
<s:property value="#attr.msg"/>
-->

<!--
	在JSP页面上可以使用EL和JSTL标签库来取值
	使用装饰者模式，连接池 全站编码
	getAttribute()增强了
-->
<c:forEach items="${ ulist }" var="user">
	${ user.username } -- ${ user.password }
</c:forEach>

<!-- 在JSP页面上，查看值栈的内部结构 -->
<s:debug></s:debug>
  </body>
</html>
