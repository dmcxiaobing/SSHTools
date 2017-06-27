package com.qq986945193.davidsshtools.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
	public void fun2(){
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		//查询的方式，HQL hibernate query language
		//HQL from User where 属性 条件
		//sql:select * from t_user where 字段 条件
		//查询当age大于多少的时候
		Query query = session.createQuery("from User where age > ?");
		//设置值  age为1
		query.setInteger(0, 1);
		//查询得到结果
		List<User> lists = query.list();
		for (User user : lists) {
			System.out.println(user);
		}
		transaction.commit();
		session.close();
	
	}
}
