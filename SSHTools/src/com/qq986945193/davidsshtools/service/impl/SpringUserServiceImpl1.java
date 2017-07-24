package com.qq986945193.davidsshtools.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.qq986945193.davidsshtools.dao.SpringUserDao1;
import com.qq986945193.davidsshtools.service.SpringUserService1;

/**
 * 一个用户的业务层。来测试spring的注解
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
//组件注解，标记类
//<bean id="userService" class="com.qq986945193.davidsshtools.service.SpringUserServiceImpl1"> == @Component(value="userService")
//@Component(value="userService")//这个注释在类上名。业务层可以用
//@Scope(value="prototype")
@Service(value="userService")
public class SpringUserServiceImpl1 implements SpringUserService1 {

	//可以使用注解的方式。这样set方法也不用写了。
	@Value(value = "注解的name")
	private String name;
	
	
	
	/**
	 * 可以使用spring的注解 。两个
	 * 	@Autowired //按类型自动装配
		@Qualifier(value="userDao")//按名称注入
	
	 *也可以用java的注解方式。只需注解一次就行了。
	 *@Resource(name = "userDao")
	 */
//	@Autowired //按类型自动装配
//	@Qualifier(value="userDao")//按名称注入
	@Resource(name = "userDao")//这是java的注解
	private SpringUserDao1 userDao1;
	
	
	public void save() {
		System.out.println("service保存成功"+name);
		userDao1.save();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


}
