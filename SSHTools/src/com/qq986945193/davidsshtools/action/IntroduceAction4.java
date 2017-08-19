package com.qq986945193.davidsshtools.action;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 完全解耦的方式，使用Servlet的API
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class IntroduceAction4 extends ActionSupport {
	/**
	 * 调用默认的方法 测试完全解耦的方式
	 */
	public String fun1(){
		//获取到Context
		ActionContext actionContext = ActionContext.getContext();
		//获取到请求参数,封装所有请求的参数
		Map<String, Object> map = actionContext.getParameters();
		//遍历获取到的数据
		Set<String> keys = map.keySet();
		for (String key : keys) {
			//通过key获取到值
			String[] valuses = (String[]) map.get(key);
			System.out.println(key+":"+Arrays.toString(valuses));
		}
		//向request域对象中存入值
		actionContext.put("msg", "完全解耦的方式");
		//获取session和application中存数据
		actionContext.getSession().put("msg", "sessionDavid");
		actionContext.getApplication().put("msg", "application");
		
		return SUCCESS;
	}
	
	
	/**
	 * 使用Servlet中原生的api进行访问测试
	 */
	public String fun2(){
//		获取到request对象
		HttpServletRequest request = ServletActionContext.getRequest();
		//设置request域，session，以及Context
		request.getSession().setAttribute("msg", "我是session域对象");
		request.setAttribute("msg", "我是request域对象");
		request.getServletContext().setAttribute("msg", "我是context对象");
		return SUCCESS;
	}
	
	/**
	 * 测试重定向action
	 */
	public String fun3(){
		System.out.println("fun3");
		return SUCCESS;
	}
	
	/**
	 * 一个update方法，让action进行调用
	 */
	public String fun4(){
		System.out.println("fun4");
		return NONE;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
