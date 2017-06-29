package com.qq986945193.davidsshtools.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.david.webtools.common.utils.CommonUtils;
import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.service.CustomerService;

/**
 * 客户的 编辑，以及查看列表的控制器
 */
public class CustomerServlet extends BaseServlet {
	private CustomerService customerService = new CustomerService();

	/**
	 * 查看客户列表
	 */
	public String catListCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到用户筛选输入的内容
		String custNameValue = request.getParameter("custName");
		// 查询数据库得到客户列表
		List<Customer> cutomerLists = customerService.catListCustomer(custNameValue);
		request.setAttribute("list", cutomerLists);
		return "f:/jsp/customer/list.jsp";
	}

	/**
	 * 根据用户ID 删除指定客户
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到要删除客户的ID
		String custId = request.getParameter("custId");
		// 删除客户
		customerService.delete(custId);

		return catListCustomer(request, response);
	}

	/**
	 * 根据用户ID 查看（编辑）指定客户
	 */
	public String editlookCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到要编辑客户的ID
		String custId = request.getParameter("custId");
		// 查询要编辑客户的信息
		Customer customer = (Customer) customerService.findCustomerById(custId);
		request.setAttribute("customer", customer);
		return "f:/jsp/customer/edit.jsp";
	}
	/**
	 * 根据用户ID 保存（编辑）指定客户
	 */
	public String editSaveCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到要编辑客户的信息
		Customer formCustomer = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		// 查询要编辑客户的信息
		customerService.updateCustomer(formCustomer);
		return catListCustomer(request, response);
	}
}
