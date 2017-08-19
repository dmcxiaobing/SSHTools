package com.qq986945193.davidsshtools.action;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.service.DemoCustomerService1;
/**
 * spring与struts2结合的action
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class DemoCustomerAction1 extends ActionSupport {

	/**
	 * 添加联系人
	 * @return 结果
	 */
	public String add(){
		try {
			//使用工厂的方式进行反射得到对象
//			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//			DemoCustomerService1 demoCustomerService1 = (DemoCustomerService1) applicationContext.getBean("demoCustomerService1");
			
			//使用web的工厂的方式.使用ServletActionContext的get方法可以获取到request，response等对象
			ServletContext servletContext = ServletActionContext.getServletContext();
			//使用使用web的工厂的方式
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			DemoCustomerService1 demoCustomerService1 = (DemoCustomerService1) webApplicationContext.getBean("demoCustomerService1");
			Customer customer = new Customer();
			customer.setCust_name("ffffffffffffffffffffff");
			demoCustomerService1.add(customer);	
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
}
