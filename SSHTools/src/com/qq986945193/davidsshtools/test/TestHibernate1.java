package com.qq986945193.davidsshtools.test;
/**
 * 测试hibernate框架
 */

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.utils.HibernateUtils;
/**
 * 事务：增删改 开启事务 可以回滚
 */
public class TestHibernate1 {
	/**
	 * 测试保存客户（添加一条记录）
	 */
	@Test
	public void testSave() {
		/**
		 * 1. 先加载配置文件 2. 创建SessionFactory对象，生成Session对象 3. 创建session对象 4. 开启事务
		 * 5. 编写保存的代码 6. 提交事务 7. 释放资源
		 */

		// 自动加载配置文件 默认加载src目录下hibernate.cfg.xml的配置文件
		// Configuration config = new Configuration();
		// config.configure();
		// 当然我们这里也可以手动加载。
		// config.addResource("com/qq986945193/davidsshtools/domain/Customer.hbm.xml");
		/**
		 * 当然这里我们直接简写
		 */
		Configuration config = new Configuration().configure();
		// 创建SessionFactory对象
		SessionFactory factory = config.buildSessionFactory();
		// 创建session对象
		Session session = factory.openSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 编写保存的代码
		Customer customer = new Customer();
		// customer.setCust_id(2L); 主键设置了自动递增
		customer.setCust_name("测试保存一条记录");
		customer.setCust_level("2");
		customer.setCust_phone("986945193");
		// 保存数据，操作对象就相当于操作数据库的表结构数据
		session.save(customer);
		// 提交事务
		transaction.commit();
		// 释放资源
		session.close();
		factory.close();
	}

	/**
	 * 利用工具类，测试保存记录
	 */
	@Test
	public void testSaveUtils() {
		// 加载配置文件，并获取Factory对象，获取Session
		Session session = HibernateUtils.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 设置数据
		Customer customer = new Customer();
		customer.setCust_name("David");
		session.save(customer);
		// 提交事务
		transaction.commit();
		// 释放资源
		session.close();
	}

	/**
	 * 测试get()方法，获取查询，通过主键来查询一条记录
	 */
	@Test
	public void testGet() {
		// 加载配置文件，获取Factory对象，获取session
		Session session = HibernateUtils.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 测试查询的方法，2个参数，第一个javabean的class对象，第二个是主键的值
		Customer customer = session.get(Customer.class, 4L);
		System.out.println(customer);
		// 提交事务
		transaction.commit();
		session.close();// 释放资源

	}

	/**
	 * 测试delete()方法，注意：删除或者修改，一定要先查询再删除或者修改
	 */
	@Test
	public void testDelete() {
		// 加载配置文件，获取Factory对象，获取session
		Session session = HibernateUtils.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 测试查询的方法，2各参数，第一个是javabean的class，第二个是主键的值
		Customer customer = session.get(Customer.class, 4L);
		// 删除客户
		session.delete(customer);
		// 提交事务并释放资源
		transaction.commit();
		session.close();

	}

	/**
	 * 测试修改的方法 注意：删除或者修改，一定要先查询再删除或者修改
	 */
	@Test
	public void testUpdate() {
		// 加载配置文件，配置Factory对象，获取session
		Session session = HibernateUtils.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 测试查询的方法 两个参数，第一个是Javabean的class，第二个是主键的值
		Customer customer = session.get(Customer.class, 5L);
		// 设置客户的信息
		customer.setCust_name("update");
		// 修改
		session.update(customer);
		// 提交事务，释放资源
		transaction.commit();
		session.close();
	}

	/**
	 * 测试添加或者修改 如果能够查询到则会修改，查询不到则则会添加。 再次提醒；一定要先查询
	 */
	@Test
	public void testSaveOrUpdate() {
		// 加载配置文件，配置Factory对象，获取session
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		Customer customer = session.get(Customer.class, 8L);
		if (customer == null) {
			customer = new Customer();
			customer.setCust_name("我是新建的");
		} else {
			customer.setCust_name("修改的");
		}
		session.saveOrUpdate(customer);
		transaction.commit();
		session.close();
	}

	/**
	 * 测试查询的方法
	 */
	@Test
	public void testQuery() {
		// 加载配置文件，配置Factory对象，获取session
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		//创建查询的接口 from 对应的实体类 javabean
		Query query = session.createQuery("from Customer");
		//查询所有的数据 select * from 表
		List<Customer> lists = query.list();
		for (Customer customer: lists) {
			System.out.println(customer);
		}
		//提交事务
		transaction.commit();
		session.close();
	}
	/**
	 * 测试保存。这里优化代码
	 */
	@Test
	public void testSaveFormat(){
		Session session  = null;
		Transaction transaction = null;
		try {
			//获取Session
			session = HibernateUtils.getSession();
			//开启事务
			transaction = session.beginTransaction();
			//执行代码
			Customer customer = new Customer();
			customer.setCust_name("优化保存");
			session.save(customer);
			//提交事务
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null) {
				transaction.rollback();
			}
		}finally {
			if (session!=null) {
				//释放资源
				session.close();
			}
		}
	}
}
