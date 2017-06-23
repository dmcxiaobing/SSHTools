package com.qq986945193.davidsshtools.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * 持久层
 */
public class CustomerDao {
	/**
	 * 添加用户到数据库
	 */
	public void saveCustomer(Customer customer) {
		//先获取session
		Session session = HibernateUtils.getSession();
		//开启事务
		Transaction transaction = session.beginTransaction();
		//设置数据
		session.save(customer);
		//提交事务
		transaction.commit();
		session.close();
	}

}
