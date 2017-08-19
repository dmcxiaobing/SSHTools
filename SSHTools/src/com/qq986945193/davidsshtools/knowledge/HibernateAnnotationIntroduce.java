package com.qq986945193.davidsshtools.knowledge;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.domain.HibernateAnnotationUser;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * 利用注解Javabean的方式进行测试hibernate
 */
public class HibernateAnnotationIntroduce {
	/**
	 * 这个如果报错，在web中是不会报错的。
	 */
	@Test
	public void fun() {
		// 加载配置文件，并获取Factory对象，获取Session
		Session session = HibernateUtils.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 设置数据
		HibernateAnnotationUser user = new HibernateAnnotationUser();
		user.setUid(1);
		user.setUsername("admin");
		session.save(user);
		// 提交事务
		transaction.commit();
		// 释放资源
		session.close();
	}
}
