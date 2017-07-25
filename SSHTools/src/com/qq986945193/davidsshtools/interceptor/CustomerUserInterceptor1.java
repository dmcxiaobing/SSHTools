package com.qq986945193.davidsshtools.interceptor;

import org.apache.struts2.ServletActionContext;
import org.springframework.core.MethodIntrospector;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.qq986945193.davidsshtools.domain.UserActionBean;
/**
 * 这是一个判断用户是否登录的interceptor
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class CustomerUserInterceptor1 extends MethodFilterInterceptor {

	/**
	 * 继承这个类，只拦截action的方法.是否登录，如果登陆了则不拦截，否则拦截。
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		System.out.println("Lanjile");
		//查看session中是否存在user对象
		UserActionBean userActionBean = (UserActionBean) ServletActionContext.getRequest().getSession().getAttribute("session_user");
		
		if (userActionBean == null) {
			//则说明没有登陆，则使其跳转到登录界面
			return "login";
		}
		//如果登陆了，则直接放行。
		return invocation.invoke();
	}

}
