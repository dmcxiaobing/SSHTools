package com.qq986945193.davidsshtools.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qq986945193.davidsshtools.domain.HibernateAnnotationUser;
import com.qq986945193.davidsshtools.utils.HibernateUtils;
/**
 * 一个用来测试的Servlet
 */
public class TestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		testHibernateAnno();
	}
	/**
	 * 测试hibernate注解javabean
	 */
	private void testHibernateAnno() {
		// 加载配置文件，并获取Factory对象，获取Session
		Session session = HibernateUtils.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 设置数据
		HibernateAnnotationUser user = new HibernateAnnotationUser();
		user.setUsername("admin");
		session.save(user);
		// 提交事务
		transaction.commit();
		// 释放资源
		session.close();
	}
}
