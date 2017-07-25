package com.qq986945193.davidsshtools.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 编写一个入门的简单的拦截器
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class IntroduceInterceptor1 extends AbstractInterceptor {

	/**
	 * interceptor用来进行拦截的
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("action方法执行之前");
		//执行下一个拦截器
		String result = invocation.invoke();
		System.out.println("action方法执行之后");
		return result;
	}

}
