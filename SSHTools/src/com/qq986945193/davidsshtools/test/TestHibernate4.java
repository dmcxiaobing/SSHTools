package com.qq986945193.davidsshtools.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.qq986945193.davidsshtools.domain.HibernateRole;
import com.qq986945193.davidsshtools.domain.HibernateUser;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
/**
 * 主要演示多对多的功能...1,写javabean,javabean的配置文件。然后映射到hibernate.cfg.xml中
 */
public class TestHibernate4 {

	/**
	 * 多对多。双向关联 配置级联:保存用户级联角色.在用户hbm.xml中<set>上配置 cascade="save-update"
	 * 如果建立了双向的关系 一定要有一方放弃外键维护权.
	 * <set name="roles" table="hibernate_user_role" cascade=
	 * "all-delete-orphan " > <set name="users" table="hibernate_user_role"
	 * inverse="true" >
	 */
	@Test
	public void fun1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		// 创建用户对象
		HibernateUser user = new HibernateUser();
		user.setUsername("admin");
		HibernateUser user2 = new HibernateUser();
		user2.setUsername("root");
		// 创建角色
		HibernateRole role = new HibernateRole();
		role.setRname("董事长");
		HibernateRole role2 = new HibernateRole();
		role2.setRname("总裁");

		// 关联 双向关联
		user.getRoles().add(role);
		user.getRoles().add(role2);
		role.getUsers().add(user);
		role2.getUsers().add(user);

		user2.getRoles().add(role);
		role.getUsers().add(user2);

		session.save(user);
		session.save(user2);
		session.save(role);
		session.save(role2);

		transaction.commit();
	}

	/**
	 * 级联保存 多对多。一方放弃外键维护。
	 */
	@Test
	public void fun2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		// 创建用户对象
		HibernateUser user = new HibernateUser();
		user.setUsername("david");
		HibernateUser user2 = new HibernateUser();
		user2.setUsername("程序员小冰");
		// 创建角色
		HibernateRole role = new HibernateRole();
		role.setRname("技术");
		HibernateRole role2 = new HibernateRole();
		role2.setRname("产品");
		//级联保存
		user.getRoles().add(role);
		user.getRoles().add(role2);
		user2.getRoles().add(role);
		//保存数据
		session.save(user);
		session.save(user2);
		transaction.commit();
	}
	/**
	 * 多对多，更改用户角色。 
	 * 
	 * 现在david用户有两个角色。技术和产品。  将david现在去掉产品的角色  
	 */
	@Test
	public void fun3() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//查询用户。david
		HibernateUser user = session.get(HibernateUser.class, 5L);
		//查询角色 产品
		HibernateRole role = session.get(HibernateRole.class, 5L);
		//移除掉产品，
		user.getRoles().remove(role);
		transaction.commit();

	}
}
