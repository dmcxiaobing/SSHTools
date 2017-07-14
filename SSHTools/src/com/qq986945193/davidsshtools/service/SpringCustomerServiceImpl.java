package com.qq986945193.davidsshtools.service;

import com.qq986945193.davidsshtools.dao.SpringCustomerDaoImpl;

/**
 * spring day1 一个业务层实现类。需要接口这里省略了
 *
 */
public class SpringCustomerServiceImpl {
	//提供成员属性，提供set方法
	private  SpringCustomerDaoImpl customerDao;
	
	public void setCustomerDao(SpringCustomerDaoImpl customerDao){
		this.customerDao = customerDao;
	}
	
	/**
	 * 一个简单的保存的方法
	 */
	public void save(){
		System.out.println("我是业务层的service。。");
		//最初的编写方式
		//new SpringCustomerDaoImpl().save();
		//spring的方式。前提配置好application.xml
		customerDao.save();
	}
}
