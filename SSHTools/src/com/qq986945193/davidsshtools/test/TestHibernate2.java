package com.qq986945193.davidsshtools.test;


import java.io.Serializable;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.qq986945193.davidsshtools.domain.Person;
import com.qq986945193.davidsshtools.domain.User;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * Hibernate测试
 */
public class TestHibernate2 {
	/**
	 * 保存数据
	 */
	@Test
	public void fun1(){
		//获取session
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		//保存用户
		User user = new User();
		user.setName("fun1");
		user.setAge(22);
		session.save(user);
		transaction.commit();
		session.close();
	}
	/**
	 * 测试UUID主键的生成策略
	 */
	@Test
	public void fun2(){
		//获取session
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		Person p = new Person();
		p.setPname("程序员小冰");
		session.save(p);
		transaction.commit();
		session.close();
	}
	
	/**
	 * 演示持久类对象的三个状态
	 */
	@Test
	public void funState(){
		//获取session
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		//持久User的对象
		//瞬时态；没有OID的值，没有被session管理，此时user对象时瞬时态对象
		User user = new User();
		user.setName("David");
		user.setAge(11);
		//使用session保存用户
		//user对象中已经存在id的值，默认的情况下，把user对象也保存到session缓存中
		Serializable id = session.save(user);
		System.out.println("主键得值"+id);
		//user持久太对象
		transaction.commit();
		//session销毁,缓存没有了
		session.close();
		//user对象存在id值，session销毁了，缓存不存在，user是脱管状态的对象
		System.out.println("id"+user.getId());
	}
	
	/**
	 * session的一级缓存
	 * 持久态的对象有自动更新数据库的能力   快照机制
	 */
	@Test
	public void fun4(){
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		//获取到持久态的对象
		User user = session.get(User.class, 1);
		//user是持久态，有自动更新数据库的能力
		System.out.println(user.getName());
		//重新设置新的名称
		user.setName("javahibernate");
		//正常编写更新代码
		//session.update(user);
		transaction.commit();
		session.close();
	}
	
	/**
	 * 证明：以及缓存是存在的
	 */
	@Test
	public void fun5(){
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		//创建对象
		User user = new User();
		user.setAge(223);
		user.setName("一级缓存存在");
		//保存用户，user一级存入到session的缓存中
		Serializable id = session.save(user);
		System.out.println(id);
		//获取对象，不会看到sql语句 证明一级缓存存在
		User u = session.get(User.class,id);
		System.out.println(u.getName());
	
		transaction.commit();
		session.close();
	}
	
	/**
	 * session.clear()-清空缓存 则会出现多次查询语句
	 */
	@Test
	public void fun6() {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		
		User user1 = session.get(User.class, 1);
		System.out.println(user1.getName());
		//清空缓存
		session.clear();
		User user2 = session.get(User.class, 1);
		System.out.println(user2.getName());
		
		transaction.commit();
		session.close();
	}
	
	/**
	 * 快照机制
	 */
	@Test
	public void run7(){
		Session session = HibernateUtils.getSession();
		Transaction tr = session.beginTransaction();
		// 获取到持久态的对象
		User user = session.get(User.class,1);
		// 重新设置新的名称
		user.setName("隔离老王");
		
		// 自动刷新缓存
		session.flush();
		
		tr.commit();
		session.close();
	}
	
	
}
