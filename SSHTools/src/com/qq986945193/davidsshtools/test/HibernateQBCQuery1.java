package com.qq986945193.davidsshtools.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
 * 离线查询DetachedCriteria
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
	
	/**
	 * QBC的条件查询
	 * 	
	  		* Restrictions.eq			-- 相等
			* Restrictions.gt			-- 大于号
			* Restrictions.ge			-- 大于等于
			* Restrictions.lt			-- 小于
			* Restrictions.le			-- 小于等于
			* Restrictions.between		-- 在之间
			* Restrictions.like			-- 模糊查询
			* Restrictions.in			-- 范围
			* Restrictions.and			-- 并且
			* Restrictions.or			-- 或者
	 */
	@Test
	public void QBC4() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//创建QBC查询接口
		Criteria criteria = session.createCriteria(Customer.class);
		//使用方法添加
		//cust_id为1的数据
		criteria.add(Restrictions.eq("cust_id", 8L));
		//大于等于0
		//criteria.add(Restrictions.ge("cust_id", 0L));
		//在0-1之间
		//criteria.add(Restrictions.between("cust_id", 0L,1L));
		List<Customer> list = criteria.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		
		transaction.commit();
	}
	/**
	 * QBC利用Restrictions.in进行查询
	 */
	@Test
	public void QBC5() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//创建QBC查询接口
		Criteria criteria = session.createCriteria(Customer.class);
		//将会查出cust_id等于10 11 12的数据
		//SQL语句：select * from tb_customer where cust_id in (10,11,12);
		List<Long> lists = new ArrayList<Long>();
		lists.add(10L);
		lists.add(11L);
		lists.add(12L);
		//这里我们直接传递一个集合，当然也可以传递一个数组
		//criteria.add(Restrictions.in("cust_id", lists));
		//传递一个数组，也是可以的
		criteria.add(Restrictions.in("cust_id", new Object[]{10L,11L,12L,13L}));
		List<Customer> customers = criteria.list();
		for (Customer customer : customers) {
			System.out.println(customer);
		}
		transaction.commit();
	}
	
	/**
	 * QBC利用Restrictions.or进行查询
	 */
	@Test
	public void QBC6() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//创建QBC查询接口
		Criteria criteria = session.createCriteria(Customer.class);
		//SQL语句是：select * from tb_customer where cust_id>29 or cust_name = '1111';
		//将会查出cust_id大于29或者cust_name=1111的数据
		criteria.add(Restrictions.or(Restrictions.gt("cust_id", 29L),Restrictions.eq("cust_name", "1111")));
		List<Customer> customers = criteria.list();
		for (Customer customer : customers) {
			System.out.println(customer);
		}
		transaction.commit();
	}
	
	/**
	 * QBC利用Restrictions.isNull 判断值是否为空
	 */
	@Test
	public void QBC7() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//创建QBC查询接口
		Criteria criteria = session.createCriteria(Customer.class);
		//判断值是否为空。这里演示找出所有为null的
		criteria.add(Restrictions.isNull("cust_source"));
		List<Customer> customers = criteria.list();
		for (Customer customer : customers) {
			System.out.println(customer);
		}
		transaction.commit();
	}
	
	/**
	 * qbc聚合函数的查询
	 */
	@Test
	public void QBC8(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//创建Criteria接口
		Criteria criteria = session.createCriteria(Linkman.class);
		//设置聚合函数的方式,求数量
		List<Number> listNumbers = criteria.setProjection(Projections.count("lkm_id")).list();
		Long count = listNumbers.get(0).longValue();
		System.out.println(count);
		transaction.commit();
	}
	
	/**
	 * QBC多个查询。如查了select count(*) from tb,又想查 select * from tb;
	 */
	@Test
	public void QBC9(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//创建Criteria接口
		Criteria criteria = session.createCriteria(Customer.class);
		//设置聚合函数。这里求总和。select SUM(c.cust_id) from cst_customer c;
		List<Number> list = criteria.setProjection(Projections.sum("cust_id")).list();
		//得到总和
		long sum = list.get(0).longValue();
		System.out.println(sum);
		//然后我们又想查询表。如select * from tb_customer;
		//这里需要将setProjection()设置为null
		criteria.setProjection(null);
		List<Customer> customers = criteria.list();
		for (Customer customer : customers) {
			System.out.println(customer);
		}
		transaction.commit();
	}
	/**
	 * 离线查询的方法 Session只是用来开启事务和查询的时候 
	 * 使用的是DetachedCriteria接口
	 */
	@Test
	public void QBC10(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//创建离线条件查询的对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		//添加条件 这里就简单添加一个降序
		detachedCriteria.addOrder(Order.desc("cust_id"));
		//调用查询的方法
		List<Customer> lists = detachedCriteria.getExecutableCriteria(session).list();
		for (Customer customer : lists) {
			System.out.println(customer);
		}
		
		
		transaction.commit();
	}
	
	
}
