package com.qq986945193.davidsshtools.test;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.junit.Test;

import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.domain.HibernateCustomer;
import com.qq986945193.davidsshtools.domain.Linkman;
import com.qq986945193.davidsshtools.utils.HibernateUtils;


/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * Hibernate查询  QBC query by Criteria
 * 分页查询：第几条记录=(当前页-1)*每页记录数 如（2-1）*10 第二页
 */
public class HibernateQBCQuery1 {

	/**
	 * qbc的基本入门查询
	 */
	@Test
	public void QBC1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//创建Qbc查询接口
		Criteria criteria = session.createCriteria(Customer.class);
		List<Customer> list = criteria.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		transaction.commit();
	}
	/**
	 * qbc查询 排序查询。调用的方法
	 */
	@Test
	public void QBC2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//创建Qbc查询接口
		Criteria criteria = session.createCriteria(Customer.class);
		//调用排序的方法，addOrder()这里使用降序，然后升序
		//criteria.addOrder(Order.desc("cust_id"));
		criteria.addOrder(Order.asc("cust_id"));
		//查询得到集合
		List<Customer> list = criteria.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		transaction.commit();
	}
	
	/**
	 * qbc查询   分页查询 方法和HQL分页的方法是一样的
	 */
	@Test
	public void QBC3() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//创建QBC查询接口
		Criteria criteria = session.createCriteria(Customer.class);
		//设置分页的方法 当前记录数为（当前页-1）*每页记录数
		criteria.setFirstResult(0);
		criteria.setMaxResults(3);
		List<Customer> list = criteria.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		
		transaction.commit();
	}
}
