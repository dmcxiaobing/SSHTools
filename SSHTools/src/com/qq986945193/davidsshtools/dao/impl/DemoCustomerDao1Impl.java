package com.qq986945193.davidsshtools.dao.impl;

import org.hibernate.Session;

import com.qq986945193.davidsshtools.dao.DemoCustomerDao1;
import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * 客户的dao实现类
 * 
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class DemoCustomerDao1Impl implements DemoCustomerDao1 {
	/**
	 * 保存客户
	 */

	@Override
	public void save(Customer customer) {
		Session session = HibernateUtils.getCurrentSession();
		session.save(customer);
	}

}
