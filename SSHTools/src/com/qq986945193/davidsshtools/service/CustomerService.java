package com.qq986945193.davidsshtools.service;

import com.qq986945193.davidsshtools.domain.Customer;

/**
 * 业务层
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
}
