package com.qq986945193.davidsshtools.action;

import com.opensymphony.xwork2.Action;
/**
 * 实现Action的接口，Action是框架提供的接口
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class IntroduceAction2 implements Action {

	@Override
	public String execute() throws Exception {
		System.out.println("IntroduceAction2实现了Action接口");
		//根据success进行跳转
//		return "success";
		//值为login
//		return LOGIN;
		//表示页面不跳转
		return null;
	}

}
