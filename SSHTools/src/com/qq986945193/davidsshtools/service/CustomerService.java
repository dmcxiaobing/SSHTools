package com.qq986945193.davidsshtools.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qq986945193.davidsshtools.dao.CustomerDao;
import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.domain.PageBean;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * 客户 业务层
 */
public class CustomerService {
	public CustomerDao dao = new CustomerDao();
	/**
	 * 将用户信息添加到数据库
	 */
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		dao.saveCustomer(customer);
	}
	/**
	 * 查看 客户列表
	 * @param custNameValue 
	 * @param pageSize 每页记录数
	 * @return 
	 */
	public List<Customer> catListCustomer(String custNameValue, String pageSize) {
		return dao.catListCustomer(custNameValue,pageSize);
	}
	/**
	 * 删除指定的客户
	 * @param custId 根据用户ID
	 */
	public void delete(String custId) {
		dao.delete(custId);
	}
	/**
	 * 根据用户ID得到用户
	 * @param custId
	 * @return 
	 */
	public Customer findCustomerById(String custId) {
		return dao.findCustomerById(custId);
	}
	/**
	 * 根据用户ID 保存（编辑）指定客户
	 */
	public void updateCustomer(Customer formCustomer) {
		dao.updateCustomer(formCustomer);
	}
	/**
	 * 得到pagebean的一些值
	 * @param pageBean 
	 * @param pageSize 
	 * @return
	 */
	public PageBean getPageBean(PageBean pageBean, String pageSize) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//得到总记录数
		pageBean.setTotalSize(dao.findTotalSize());
		//得到总页数
		pageBean.setTotalPage(dao.findTotalPage(pageSize));
		
		transaction.commit();
		return pageBean;
	}
}
