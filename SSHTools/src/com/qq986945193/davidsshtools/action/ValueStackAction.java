package com.qq986945193.davidsshtools.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.qq986945193.davidsshtools.domain.OgnlUser;

/**
 * 演示值栈对象的目录结构
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class ValueStackAction extends ActionSupport {
	private OgnlUser user = new OgnlUser();

	public OgnlUser getUser() {
		return user;
	}

	public void setUser(OgnlUser user) {
		this.user = user;
	}
	
	/**
	 * 演示值栈
	 */
	public String introduce() throws Exception{
		//使用获取值对象
	/*	HttpServletRequest request = ServletActionContext.getRequest();
		ValueStack vStack = (ValueStack) request.getAttribute("struts.valueStack");
		System.out.println(vStack);*/
		
		//获取到值栈对象，先获取到ActionContext对象
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		System.out.println(valueStack);
		//栈顶压字符串David
		valueStack.push("david");
		//栈顶：
		valueStack.set("msg","msgzhanding");
		valueStack.set("info", "infomsg");
		return "";
	}
	
	
	/**
	 * 演示从值栈中获取值
	 */
	public String save() throws Exception{
		
		//获取值栈
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		//压栈
		valueStack.push("david");
		valueStack.set("msg", "msg压栈");
		//创建User对象
		OgnlUser user = new OgnlUser();
		user.setUsername("username");
		user.setPassword("pwd");
		//压栈
		valueStack.push(user);
		valueStack.set("user", user);
		List<OgnlUser> ulist = new ArrayList<OgnlUser>();
		ulist.add(new OgnlUser("熊大","123"));
		ulist.add(new OgnlUser("熊二","456"));
		ulist.add(new OgnlUser("熊三","789"));
		
		// 把ulist集合压栈
		// vs.push(ulist);
		
		// set方法进行压栈
		valueStack.set("ulist", ulist);
		
		/*
		// 从context栈中获取值，底层已经封装到request session对象，操作就是map集合
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("msg", "美美");
		request.getSession().setAttribute("msg", "小风");*/
		
		return "ognlsuccess";
	}
	
	/**
	 * 查询所有的客户
	 * @return
	 */
	public String list(){
//		List<Customer> clist = new CustomerService().findAll();
//		// 把clist压入到值栈中
//		ValueStack vs = ActionContext.getContext().getValueStack();
//		// 压栈，默认的规范：压入的是集合，一般使用set方法，压入是对象，使用push对象
//		vs.set("clist", clist);
		return "list";
	}
	
}
