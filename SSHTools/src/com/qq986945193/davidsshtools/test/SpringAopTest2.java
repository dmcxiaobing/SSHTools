package com.qq986945193.davidsshtools.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qq986945193.davidsshtools.dao.SpringUserDao2;

/**
 * 测试AOP的功能.利用XML的方式
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springApplicationContext3.xml")
//这个是检测xml配置@ContextConfiguration("classpath:springApplicationContext.xml")
public class SpringAopTest2 {

	//利用注解的方式进行注入
	@Resource(name = "userDao")
	private SpringUserDao2 userDao;

	/**
	 * 测试aop的功能
	 */
	@Test
	public void fun1(){
	//userDao = new SpringUserDaoImpl2();
	userDao.save();
	userDao.update();
	}
}
