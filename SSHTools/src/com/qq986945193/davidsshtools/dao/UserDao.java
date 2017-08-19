package com.qq986945193.davidsshtools.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.qq986945193.davidsshtools.domain.UserActionBean;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * 用户的持久层
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class UserDao {
	/**
	 * 用户登陆的业务层
	 * 	通过用户名和密码查询数据库
	 */
	public UserActionBean login(UserActionBean userform) {
		//先获取session
		Session session = HibernateUtils.getCurrentSession();
		//使用用户名和密码进行查询
		Query query = session.createQuery("from UserActionBean where username = ? and password = ?");
		//设置参数
		query.setParameter(0,userform.getUsername());
		query.setParameter(1, userform.getPassword());
		//查询
		List<UserActionBean> list = query.list();
		//如果有数据，并且不为空
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}
