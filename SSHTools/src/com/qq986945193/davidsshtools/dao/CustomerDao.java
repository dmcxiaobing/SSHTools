package com.qq986945193.davidsshtools.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * 客户 持久层
 */
public class CustomerDao {
	/**
	 * 添加用户到数据库
	 */
	public void saveCustomer(Customer customer) {
		// 先获取session
		Session session = HibernateUtils.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 设置数据
		session.save(customer);
		// 提交事务
		transaction.commit();
		session.close();
	}

	/**
	 * 查看 所有客户列表
	 */
	public List<Customer> catListCustomer() {
		// 获取session
		Session session = HibernateUtils.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 查询
		Criteria createCriteria = session.createCriteria(Customer.class);
		List<Customer> lists = createCriteria.list();
		transaction.commit();
		session.close();
		return lists;

	}

}
