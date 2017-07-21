package com.qq986945193.davidsshtools.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qq986945193.davidsshtools.dao.DemoCustomerDao1;
import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * 客户的管理servie业务层实现
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class DemoCustomerService1Impl implements DemoCustomerService1 {
	//客户的持久层，利用set方法进行注解
	private DemoCustomerDao1 customerDao;
	
	public void setCustomerDao(DemoCustomerDao1 customerDao) {
		this.customerDao = customerDao;
	}

	/**
	 * 添加用户
	 */
	@Override
	public void add(Customer customer) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			customerDao.save(customer);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}

}
