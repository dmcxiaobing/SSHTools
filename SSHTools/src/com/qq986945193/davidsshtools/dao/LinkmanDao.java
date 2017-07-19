package com.qq986945193.davidsshtools.dao;

import java.util.List;

import org.hibernate.Session;

import com.qq986945193.davidsshtools.domain.HibernateCustomer;
import com.qq986945193.davidsshtools.domain.Linkman;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * 联系人的持久层
 * 
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class LinkmanDao {
	/**
	 * 根据custId查询到客户
	 */
	public HibernateCustomer findByCustid(String custId) {
		/**
		 * 使用session
		 */
		Session session = HibernateUtils.getCurrentSession();
		// 查询
		return session.get(HibernateCustomer.class, Long.parseLong(custId));
	}

	/**
	 * 保存联系人
	 * 
	 * @param formlinkman
	 *            用户输入的表单联系人信息
	 */
	public void save(Linkman formlinkman) {
		Session session = HibernateUtils.getCurrentSession();
		// 保存
		session.save(formlinkman);

	}

	/**
	 * 查询所有联系人
	 * @return
	 */
	public List<Linkman> findAll() {
		Session session = HibernateUtils.getCurrentSession();
		List<Linkman> linkmans =  session.createQuery("from Linkman").list();
		System.out.println(":;d"+linkmans.size());
		return linkmans;
	}

}
