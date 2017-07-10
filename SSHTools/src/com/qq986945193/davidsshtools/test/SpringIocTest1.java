package com.qq986945193.davidsshtools.test;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.qq986945193.davidsshtools.service.SpringUserService;
import com.qq986945193.davidsshtools.service.SpringUserServiceImpl;

/**
 * 测试IOC的程序
 */
public class SpringIocTest1 {
	/**
	 * 原先最基础的方式
	 */
	@Test
	public void fun1(){
		//创建实现类
		SpringUserServiceImpl us = new SpringUserServiceImpl();
		us.setName("我是基础的");
		//则会输出：hello spring我是基础的
		//当然也可以 
		SpringUserService userService = new SpringUserServiceImpl();
		us.sayHello();
		//则会输出hello springnull 因为。userService并没有给name赋值
		userService.sayHello();
	}
	/**
	 * 我们这里使用spring框架的方式。前提配置好applicationContext中的属性
	 */
	@Test
	public void funSpring1(){
		//创建工厂，加载核心配置文件
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		//从工厂中获取到对象,根据ID取出对象
		SpringUserServiceImpl userServiceImpl = (SpringUserServiceImpl) applicationContext.getBean("userService");
		//如果这样的话，就是以接口的方式获取到对象示例
		SpringUserService userService = (SpringUserService) applicationContext.getBean("userService");
		//调用对象的方法执行。
		userServiceImpl.sayHello();
		userService.sayHello();
	}
	/**
	 * 使用已过时的工厂版本BeanFactory
	 */
	@Test
	public void funBeanFactory(){
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		SpringUserService userService = (SpringUserService) factory.getBean("userService");
		userService.sayHello();
	}
	
	/**
	 * 演示关闭销毁的方法。销毁对象
	 */
	@Test
	public void funSpringClose(){
		//创建工厂，加载核心配置文件
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		//从工厂中获取到对象,根据ID取出对象
		SpringUserServiceImpl userServiceImpl = (SpringUserServiceImpl) classPathXmlApplicationContext.getBean("userService");
		//调用对象的方法执行。
		userServiceImpl.sayHello();
		//如果调用classPathXmlApplicationContext的关闭方法，即关闭工厂，则对象即会销毁
		classPathXmlApplicationContext.close();
	}
}
