package com.qq986945193.davidsshtools.test;

import java.util.Arrays;
/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.qq986945193.davidsshtools.domain.HibernateCustomer;
import com.qq986945193.davidsshtools.domain.User;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * hibernate的一些多表查询操作
 */
public class TestHibernateQuery2 {
	
	/**
	 * 查询的客户，客户和联系人关联 select * from cst_hibernate_customer c,cst_linkman l where
	 * c.cust_id = l.lkm_cust_id;
	 */
	@Test
	public void fun1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		// 内连接的查询
		Query query = session.createQuery("from HibernateCustomer c ,Linkman l inner join c.cust_id=l.lkm_cust_id");
		// 默认的返回值是数组，得到查询结果
		List<Object[]> list = query.list();
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}

		transaction.commit();
	}
}
