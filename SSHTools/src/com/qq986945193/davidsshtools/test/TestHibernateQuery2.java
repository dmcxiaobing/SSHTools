package com.qq986945193.davidsshtools.test;

import java.util.Arrays;
/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.domain.HibernateCustomer;
import com.qq986945193.davidsshtools.domain.User;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * hibernate的一些多表查询操作
 * 关键字fetch 可以将数据封装到Javabean中。
 * 
 */
public class TestHibernateQuery2 {

	/**
	 * 查询的客户，客户和联系人关联  内连接查询 默认没有封装数据到Javabean对象中
	 * select * from cst_hibernate_customer c,cst_linkman l where
	 * c.cust_id = l.lkm_cust_id;
	 */
	@Test
	public void fun1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		// 内连接的查询
		Query query = session.createQuery("from HibernateCustomer hc inner join hc.linkmans");

		// 默认的返回值是数组，得到查询结果
		List<Object[]> list = query.list();
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}
		transaction.commit();
	}
	/**
	 * 内连接查询，默认返回的数据封装到了javabean对象中
	 * 需要使用关键字fetch 
	 */
	@Test
	public void fun2(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//内连接查询。
		Query query = session.createQuery("from HibernateCustomer hc inner join fetch hc.linkmans");
		List<HibernateCustomer> lists = query.list();
		System.out.println(lists.size());
		for (HibernateCustomer hibernateCustomer : lists) {
			System.out.println(hibernateCustomer);
		}
		transaction.commit();
	}
	/**
	 * 内连接查询解决数据的重复问题，需要重新新建一个set集合 只要有了一个就回去出掉
	 */
	@Test
	public void fun3(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//设置内连接查询，并需要返回结果为javabean封装后的，fetch
		Query query = session.createQuery("from HibernateCustomer hc inner join fetch hc.linkmans");
		//去掉重复数据
		List<HibernateCustomer> list = query.list();
		Set<HibernateCustomer> sets = new HashSet<HibernateCustomer>(list);
		System.out.println(sets.size());
		for (HibernateCustomer hibernateCustomer : sets) {
			System.out.println(hibernateCustomer);
		}
		transaction.commit();
	}

}
