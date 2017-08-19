package com.qq986945193.davidsshtools.test;
/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.qq986945193.davidsshtools.domain.SpringCar1;
import com.qq986945193.davidsshtools.domain.SpringCar2;
import com.qq986945193.davidsshtools.domain.SpringPerson;
import com.qq986945193.davidsshtools.domain.SpringUser1;
import com.qq986945193.davidsshtools.service.SpringUserService;
import com.qq986945193.davidsshtools.service.impl.SpringCustomerServiceImpl;
import com.qq986945193.davidsshtools.service.impl.SpringUserServiceImpl;

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
/*======================================================================================*/
	/**
	 * 原始的方式操作我们的业务层和持久层
	 */
	@Test
	public void fun2(){
		SpringCustomerServiceImpl serviceImpl = new SpringCustomerServiceImpl();
		serviceImpl.save();
	}
	/**
	 * 利用spring的方式操作我们的业务层和持久层
	 */
	@Test
	public void fun3(){
		//创建工厂，加载配置文件。此时，CustomerDaoImpl创建了，CustomerServiceImpl也被创建了
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		SpringCustomerServiceImpl serviceImpl = (SpringCustomerServiceImpl) applicationContext.getBean("customerService");
		serviceImpl.save();
	}
/*======================================================================================*/
	/**
	 * 以构造方法获取到javabean对象
	 */
	@Test
	public void fun4(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		SpringCar1 car1 = (SpringCar1) applicationContext.getBean("car1");
		System.out.println(car1);
	}
	
	/**
	 * 以构造方法获取到javabean对象 javabean之间有依赖
	 */
	@Test
	public void fun5() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		SpringPerson person = (SpringPerson) applicationContext.getBean("person");
		System.out.println(person);
	}
	/**
	 * 以set方式测试javabean
	 */
	@Test
	public void fun6(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		SpringCar2 car2 = (SpringCar2) applicationContext.getBean("car2");
		System.out.println(car2);
	}
	/**
	 * 测试注入集合
	 */
	@Test
	public void fun7(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		SpringUser1 user1 = (SpringUser1) applicationContext.getBean("user");
		System.err.println(user1);
	}
	
	/**
	 * 测试加载多个配置文件。加载多个applicationContext.xml
	 */
	@Test
	public void fun8(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext2.xml");
		SpringUser1 user1 = (SpringUser1) applicationContext.getBean("user");
		System.out.println(user1);
	}
	
}


