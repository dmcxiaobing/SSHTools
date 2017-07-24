package com.qq986945193.davidsshtools.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qq986945193.davidsshtools.service.SpringUserService1;
import com.qq986945193.davidsshtools.service.impl.SpringUserServiceImpl1;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
/**
 * 注解的简单使用介绍
 */
public class SpringIocTest2 {

	/**
	 * 原始的方式，直接用new
	 */
	@Test
	public void fun1() {
		SpringUserService1 service1 = new SpringUserServiceImpl1();
		service1.save();
	}
	
	/**
	 * 使用注解的 方式。首先开启注解扫描，然后进行注解
	 */
	@Test
	public void fun2() {
		//获取工厂，加载配置我那件
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext3.xml");
		//获取到SpringUserService1对象
		SpringUserService1 userService1 = (SpringUserService1)applicationContext.getBean("userService");
		userService1.save();
	}
	
	
}
