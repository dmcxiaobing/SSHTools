package com.qq986945193.davidsshtools.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.service.CustomerService;

/**
 * 客户的 编辑，以及查看列表的控制器
 */
public class CustomerServlet extends BaseServlet {
	private CustomerService customerService = new CustomerService();
	/**
	 * 查看客户列表
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String catListCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Customer> cutomerLists = customerService.catListCustomer();
		request.setAttribute("list", cutomerLists);
		return "f:/jsp/customer/list.jsp";
	}
}
