package com.qq986945193.davidsshtools.test;

import java.util.Arrays;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * SQL的查询方式
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class HibernateSQLQuery1 {

	/**
	 * 基本的SQL查询,这里得到Object，没有封装javabean数据
	 */
	@Test
	public void fun1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//创建SQLQuery的接口
		SQLQuery sqlQuery = session.createSQLQuery("select * from cst_customer");
		//查询数据
		List<Object[]> list = sqlQuery.list();
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}
		transaction.commit();
	}
	/**
	 * 基本的查询，将javabean封装到sqlquery中
	 */
	@Test
	public void fun2(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//创建SQLQuery的接口
		SQLQuery sqlQuery = session.createSQLQuery("select * from cst_customer");
		//封装javabean数据， 设置
		sqlQuery.addEntity(Customer.class);
		//查询。得到查询结果
		List<Customer> lists = sqlQuery.list();
		for (Customer customer : lists) {
			System.out.println(customer);
		}
		transaction.commit();
	}
	
}
