package com.qq986945193.davidsshtools.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate框架简单封装工具类
 */
public class HibernateUtils {
	private static final Configuration CONFIG;
	private static final SessionFactory FACTORY;

	/**
	 * 编写静态代码块
	 */
	static {
		/**
		 * 加载XML的配置文件
		 */
		CONFIG = new Configuration().configure();
		FACTORY = CONFIG.buildSessionFactory();
	}

	/**
	 * 从工厂中获取Session对象
	 */

	public static Session getSession() {
		return FACTORY.openSession();
	}
}
