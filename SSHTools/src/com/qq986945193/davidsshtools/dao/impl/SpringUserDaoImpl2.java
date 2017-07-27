package com.qq986945193.davidsshtools.dao.impl;
/**
 * 用来演示AOP的功能。增强
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class SpringUserDaoImpl2 implements com.qq986945193.davidsshtools.dao.SpringUserDao2 {
	/**
	 * 保存
	 */
	public void save() {
		//演示报异常
		//int num = 10/0;
		System.out.println("保存。。");
	}

	public void update() {
		System.out.println("update...");
	}

}
