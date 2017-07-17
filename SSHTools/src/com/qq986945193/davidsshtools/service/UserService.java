package com.qq986945193.davidsshtools.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qq986945193.davidsshtools.dao.UserDao;
import com.qq986945193.davidsshtools.domain.UserActionBean;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * 用户的业务层
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class UserService {
	private UserDao dao = new UserDao();
	/**
	 * 用户登陆
	 * @param userform 表单信息
	 */
	public UserActionBean login(UserActionBean userform) {
		//使用事务/开启事务/关闭事务
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		UserActionBean userActionBean = null;
		try {
			userActionBean = dao.login(userform);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
		return userActionBean;
	}

}
