package com.qq986945193.davidsshtools.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

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
	 * 查询所有联系人 利用离线查询
	 * @param detachedCriteria 
	 * @return
	 */
	public List<Linkman> findAllByDetachedCriteria(DetachedCriteria detachedCriteria) {
		Session session = HibernateUtils.getCurrentSession();
		List<Linkman> linkmans =  detachedCriteria.getExecutableCriteria(session).list();
		return linkmans;
	}

	/**
	 * 查询出总记录数 formName 用户输入的信息
	 */
	public int pageCountQuery(String formName) {
		Session session = HibernateUtils.getCurrentSession();
		//得到查询接口
		Criteria criteria = session.createCriteria(Linkman.class);
		/*if (formName!=null && !formName.trim().isEmpty()) {
			//添加模糊查询，用户输入的信息
			criteria.add(Restrictions.like("lkm_name", "%"+formName+"%"));
		}*/
		//设置聚合查询，得到条目数。最后一定要调用查询的方法获得list
		List<Number> list = criteria.setProjection(Projections.count("lkm_id")).list();
		//得到总记录数
		if (list.size()>0) {
			int totalSize = list.get(0).intValue();
			return totalSize;
		}
		return 0;
		
	}
	/**
	 * 查询所有联系人
	 * @param formName
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Linkman> findAll(String formName, int currentPage, int pageSize) {
		Session session = HibernateUtils.getCurrentSession();
		//得到查询接口
		Criteria criteria = session.createCriteria(Linkman.class);
		if (formName!=null && !formName.trim().isEmpty()) {
			//添加模糊查询，用户输入的信息
			criteria.add(Restrictions.like("lkm_name", "%"+formName+"%"));
		}
		//设置分页 分页查询：第几条记录=(当前页-1)*每页记录数 如（2-1）*10 第二页
		criteria.setFirstResult((currentPage-1)*pageSize);
		criteria.setMaxResults(pageSize);
		List<Linkman> linkmans = criteria.list();
		return linkmans;
	}

}
