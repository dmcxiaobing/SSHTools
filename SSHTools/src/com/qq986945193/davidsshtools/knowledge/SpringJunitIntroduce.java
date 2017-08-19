package com.qq986945193.davidsshtools.knowledge;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qq986945193.davidsshtools.service.SpringUserService1;

/**
 * 利用spring与Junit进行写测试类
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@RunWith(SpringJUnit4ClassRunner.class)//注入测试类
@ContextConfiguration("classpath:applicationContext3.xml")//注入加载配置文件
public class SpringJunitIntroduce {
	
	//利用java进行注解
	@Resource(name="userService")
	private SpringUserService1 userService1;

	@Test
	public void fun1(){
		//原来获取工厂，加载配置文件。getBean()，这里直接注入然后得到
		userService1.save();
	}
}
