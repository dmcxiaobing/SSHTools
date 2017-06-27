package com.qq986945193.davidsshtools.test;
/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.qq986945193.davidsshtools.domain.User;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * hibernate的一些查询操作
 */
public class TestHibernateQuery {
	/**
	 * createQuery()得到集合
	 */
	@Test
	public void fun1() {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		// 查询的方式
		Query query = session.createQuery("from User");
		List<User> listsList = query.list();
		for (User user : listsList) {
			System.out.println(user);
		}

		transaction.commit();
		session.close();
	}

	/**
	 * createQuery()查询，并添加条件。得到集合
	 */
	@Test
	public void fun2() {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		// 查询的方式，HQL hibernate query language
		// HQL from User where 属性 条件
		// sql:select * from t_user where 字段 条件
		// 查询当age大于多少的时候
		Query query = session.createQuery("from User where age > ?");
		// 设置值 age为1
		query.setInteger(0, 1);
		// 查询得到结果
		List<User> lists = query.list();
		for (User user : lists) {
			System.out.println(user);
		}
		transaction.commit();
		session.close();
	}
	/**
	 * createQuery()查询，并添加条件。得到集合
	 */
	@Test
	public void fun3() {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		// 查询的方式，HQL hibernate query language
		// HQL from User where 属性 条件
		// sql:select * from t_user where 字段 条件
		Query query = session.createQuery("from User where name like ?");
		//设置值   名字带有一的
		query.setString(0, "%一%");
		List<User> lists = query.list();
		for (User user : lists) {
			System.out.println(user);
		}
		transaction.commit();
		session.close();
		
	}
	/**
	 * createQuery()查询，并添加变量条件。得到集合
	 */
	@Test
	public void fun4(){
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		// 查询的方式，HQL hibernate query language
		// HQL from User where 属性 条件
		// sql:select * from t_user where 字段 条件
		//设置一个参数变量
		Query query = session.createQuery("from User where age > :mage");
		//给参数变量赋值
		query.setInteger("mage",18);
		List<User> lists = query.list();
		for (User user : lists) {
			System.out.println(user);
		}
		transaction.commit();
		session.close();
	}
	/**
	 * 查询，利用Criteria接口，条件查询 非常合适。
	 */
	@Test
	public void fun5() {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		/**
		 * 先获取到Criteria接口
		 *
		 */
		Criteria criteria = session.createCriteria(User.class);
		//这里，不添加条件，直接查询到所有的数据
		List<User> lists = criteria.list();
		for (User user : lists) {
			System.out.println(user);
		}
		transaction.commit();
		session.close();
	}
	/**
	 * 按条件查询，利用Criteria接口
	 */
	@Test
	public void fun6() {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		//先获取到Criteria接口
		Criteria criteria = session.createCriteria(User.class);
		//添加查询的条件 select * from t_user where age > 18
		//Criterion是Hibernate提供的条件查询的对象，想传入的条件使用工具类Restrictions
		//Restrictions提供的静态的方法，拼接查询的条件
		criteria.add(Restrictions.gt("age", 18));
		//继续添加条件  名字带有一
		criteria.add(Restrictions.like("name", "%一%"));
		//调用查询的方法
		List<User> lists = criteria.list();
		System.out.println(lists);
		transaction.commit();
		session.close();
	}
}
