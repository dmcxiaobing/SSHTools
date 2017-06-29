package com.qq986945193.davidsshtools.service;

import java.util.List;

import com.qq986945193.davidsshtools.dao.CustomerDao;
import com.qq986945193.davidsshtools.domain.Customer;

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
	 * @return 
	 */
	public List<Customer> catListCustomer(String custNameValue) {
		return dao.catListCustomer(custNameValue);
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
}
