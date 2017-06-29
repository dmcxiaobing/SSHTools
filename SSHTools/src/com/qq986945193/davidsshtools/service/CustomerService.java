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
	 * @return 
	 */
	public List<Customer> catListCustomer() {
		return dao.catListCustomer();
	}
}
