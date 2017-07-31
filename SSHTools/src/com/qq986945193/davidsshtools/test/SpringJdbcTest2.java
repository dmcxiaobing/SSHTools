package com.qq986945193.davidsshtools.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qq986945193.davidsshtools.service.SpringJdbcAccountService1;

/**
 * 利用转账的小示例，来演示JDBCTemplate事务的功能
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@RunWith(SpringJUnit4ClassRunner.class)
//这是手动进行编写事务管理
//@ContextConfiguration("classpath:springjdbcApplicationContext3.xml")
//交于aop进行管理事务，配置切入点
//@ContextConfiguration("classpath:springjdbcApplicationContext4.xml")
//采用注解的方式进行管理事务
@ContextConfiguration("classpath:springjdbcApplicationContext5.xml")
public class SpringJdbcTest2 {
	
	@Resource(name="accountService")
	private SpringJdbcAccountService1 service;
	/**
	 * 转账的功能，利用aop的方式，
	 */
	@Test
	public void fun1(){
		//调用支付的方法
		//service.pay("zs","li",22);
		//service.pay2("zs", "li", 1);
		service.pay3("zs", "li", 1);
	}
}
