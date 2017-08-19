package com.qq986945193.davidsshtools.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.qq986945193.davidsshtools.dao.SpringUserDao1;

/**
 * 一个用户的持久层。来测试spring的注解
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
//组件注解，标记类 持久层，所以用持久层的注解。Repository
@Repository(value = "userDao")
public class SpringUserDaoImpl1 implements SpringUserDao1 {

	public void save() {
		System.out.println("dao保存成功");
	}


}
