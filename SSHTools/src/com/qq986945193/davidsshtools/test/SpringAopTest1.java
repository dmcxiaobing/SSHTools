package com.qq986945193.davidsshtools.test;

import org.junit.Test;

import com.qq986945193.davidsshtools.dao.SpringUserDao1;
import com.qq986945193.davidsshtools.dao.SpringUserDao2;
import com.qq986945193.davidsshtools.dao.impl.SpringUserDaoImpl1;
import com.qq986945193.davidsshtools.dao.impl.SpringUserDaoImpl2;
import com.qq986945193.davidsshtools.utils.CglibUtils;
import com.qq986945193.davidsshtools.utils.JDKProxyUtils;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
/*
 * 测试aop增强的 示例：比如我需要在方法执行后添加访问日志
 */
public class SpringAopTest1 {

	/**
	 * 这是利用jdk代理，自己写的。最基本的方法
	 */
	@Test
	public void fun1() {
		// 得到对象，调用我们的方法
		SpringUserDao2 userDao = new SpringUserDaoImpl2();
		userDao.save();
		userDao.update();
		System.out.println("******************************");
		// 我们不用更改源代码，直接就可以记录方法。实用工具类获取到代理对象
		SpringUserDao2 proxy = JDKProxyUtils.getProxy(userDao);
		// 调用代理对象的方法
		proxy.save();
		proxy.update();
	}
	

	/**
	 * 这是利用cglib代理，自己写的。最基本的方法
	 * 	保存。。
		update...
		******************************
		记录日志
		保存。。
		update...
	 */
	@Test
	public void fun2() {
		// 得到对象，调用我们的方法
		SpringUserDao2 userDao = new SpringUserDaoImpl2();
		userDao.save();
		userDao.update();
		System.out.println("******************************");
		// 我们不用更改源代码，直接就可以记录方法。实用工具类获取到代理对象
		SpringUserDao2 proxy = CglibUtils.getProxy();
		// 调用代理对象的方法
		proxy.save();
		proxy.update();
	}
}
