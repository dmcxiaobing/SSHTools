package com.qq986945193.davidsshtools.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import com.qq986945193.davidsshtools.dao.LinkmanDao;
import com.qq986945193.davidsshtools.domain.HibernateCustomer;
import com.qq986945193.davidsshtools.domain.Linkman;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * 联系人的service
 * 
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class LinkmanService {
	private LinkmanDao linkmanDao = new LinkmanDao();

	/**
	 * 添加联系人 编写业务。 先根据客户ID查询出客户，然后设置到联系人中，最后保存联系人
	 * 
	 * @param formlinkman
	 * @param custId
	 */
	public void addLinkman(Linkman formlinkman, String custId) {
		// 开启事务，然后调用持久层的代码，最后提交事务
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			// 根据ID查询到客户，然后将客户设置到联系人
			HibernateCustomer hibernateCustomer = linkmanDao.findByCustid(custId);
			formlinkman.setCustomer(hibernateCustomer);
			// 保存联系人
			linkmanDao.save(formlinkman);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}

	}

	/**
	 * 查询所有联系人
	 * @param detachedCriteria 
	 * 
	 * @return
	 */
	public List<Linkman> findAll(DetachedCriteria detachedCriteria) {
		// 开启事务，然后调用持久层的代码，最后提交事务
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			// 根据ID查询到客户，然后将客户设置到联系人
			List<Linkman> lists = linkmanDao.findAll(detachedCriteria);
			transaction.commit();
			return lists;
		} catch (Exception e) {
			transaction.rollback();
			return null;
		}
	}

}
