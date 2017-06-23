package com.qq986945193.davidsshtools.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.service.CustomerService;
/**
 * 新增用户，保存信息 添加用户的控制器
 */
public class SaveCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取表单数据
		Map<String, String[]> map = request.getParameterMap();
			try {
				Customer customer = new Customer();
				//封装数据，使用BeanUtils工具，
				BeanUtils.populate(customer, map);
				/**
				 * 依赖业务层 service
				 */
				new CustomerService().saveCustomer(customer);
				System.out.println("添加客户成功了");
				request.getRequestDispatcher("/jsp/success.jsp").forward(request,response);
			} catch (Exception e) {
				e.printStackTrace();
				//添加失败，转发到错误页面
				request.getRequestDispatcher("/jsp/error.jsp").forward(request,response);
			} 
		
	}

}
