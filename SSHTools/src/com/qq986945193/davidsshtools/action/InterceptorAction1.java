package com.qq986945193.davidsshtools.action;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 这只是一个用来测试interceptor的action
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class InterceptorAction1 extends ActionSupport {
	
	public String testInterceptor(){
		System.err.println("我是nterceptorAction，我正常执行...");
		return NONE;
	}
}
