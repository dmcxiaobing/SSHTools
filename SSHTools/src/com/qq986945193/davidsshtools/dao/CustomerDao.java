package com.qq986945193.davidsshtools.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.domain.PageBean;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * 客户 持久层
 */
public class CustomerDao {
	/**
	 * 添加用户到数据库
	 */
	public void saveCustomer(Customer customer) {
		// 先获取session
		Session session = HibernateUtils.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 设置数据
		session.save(customer);
		// 提交事务
		transaction.commit();
		session.close();
	}

	/**
	 * 查看 所有客户列表
	 * 
	 * @param custNameValue 筛选时用户输入的内容
	 * @param pageSize 每页显示记录数
	 */
	public List<Customer> catListCustomer(String custNameValue, String pageSize) {
		// 获取session
		Session session = HibernateUtils.getSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 查询
		Criteria createCriteria = session.createCriteria(Customer.class);
		// 如果筛选的条件不为空
		if (custNameValue != null && !(custNameValue.trim().isEmpty())) {
			// 添加查询条件
			createCriteria.add(Restrictions.like("cust_name", "%" + custNameValue + "%"));
		}
		//如果pageSize 为null,则默认为10
		int pagesize = 0;
		if (pageSize == null) {
			pagesize = 10;
		}else {
			pagesize = Integer.parseInt(pageSize);
		}
		//(当前页-1）*Pagesize
//		createCriteria.setFirstResult(1);
//		createCriteria.setMaxResults(pagesize);
//		
		// 查询得到客户集合
		List<Customer> lists = createCriteria.list();
		transaction.commit();
		session.close();
		return lists;

	}

	/**
	 * 根据客户ID 删除指定的客户 注：先查询后修改或者删除
	 */
	public void delete(String custId) {
		// 获取session
		Session session = HibernateUtils.getCurrentSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 查询
		// 因为只能查出来一个，所以直接取出
		Customer customer = session.get(Customer.class, Long.parseLong(custId));
		session.delete(customer);
		transaction.commit();
	}

	/**
	 * 根据custId查询数据库得到客户信息
	 */
	public Customer findCustomerById(String custId) {
		// 获取session
		Session session = HibernateUtils.getCurrentSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		// 查询
		Customer customer = session.get(Customer.class, Long.parseLong(custId));
		transaction.commit();
		return customer;
	}

	/**
	 * 根据用户ID 保存（编辑）指定客户 注：先查询后修改或者删除 这里是指更改某一个属性
	 */
	public void updateCustomer(Customer formCustomer) {
		// 获取session
		Session session = HibernateUtils.getCurrentSession();
		// 开启事务
		Transaction transaction = session.beginTransaction();
		session.update(formCustomer);
		transaction.commit();
	}

	/**
	 * 查询所有的记录数
	 */
	public Integer findTotalSize() {
		Session session = HibernateUtils.getCurrentSession();
		Query query = session.createQuery("select count(c.cust_id) from Customer c");
		List<Number> list = query.list();
		int count = list.get(0).intValue();
		return count;
	}

	/**
	 * 得到总页数
	 */
	public Integer findTotalPage(String pageSize) {
		int p = 0;
		if (pageSize == null) {
			p = 10;
		}
		return getTotalPage(findTotalSize(), p);
	}
	
	/**
	 * 返回总页数
	 */
	public Integer getTotalPage(Integer totalSize,Integer pageSize) {
		// 总记录数 除以每页记录数
		int tp = totalSize / pageSize;
		return totalSize % pageSize == 0 ? tp : tp + 1;
	}

}
