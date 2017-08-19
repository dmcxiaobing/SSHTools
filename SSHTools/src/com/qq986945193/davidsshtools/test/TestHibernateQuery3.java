package com.qq986945193.davidsshtools.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.domain.HibernateCustomer;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * 优化查询的一些基本介绍 延迟加载等 也可以在在many-to-one标签上 查询策略的优化
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class TestHibernateQuery3 {
	/**
	 * 类级别的延迟加载。
	 * 需要使用session.load()默认情况是支持延迟加载的
	 * 如果使用get等方法不是延迟加载的
	 */
	@Test
	public void fun1(){
		//查询客户
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//使用get方法 没有延迟加载
		//HibernateCustomer hibernateCustomer = session.get(HibernateCustomer.class, 5L);
		HibernateCustomer hibernateCustomer = session.load(HibernateCustomer.class, 5L);
		System.out.println("*******david**********");
		System.out.println(hibernateCustomer.getCust_name());
		transaction.commit();
	}
	/**
	 * 关联级别的延迟加载
	 * 说的是客户下的联系人的集合
	 */
	@Test
	public void run3(){
		// 查询客户
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 先查询1号客户
		HibernateCustomer c1 = session.get(HibernateCustomer.class, 1L);
		System.out.println("=============================");
		// 看客户下所有的联系人
		System.out.println(c1.getLinkmans().size());
		tr.commit();
	}
	
}
