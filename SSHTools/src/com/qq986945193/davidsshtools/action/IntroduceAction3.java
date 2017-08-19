package com.qq986945193.davidsshtools.action;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 继承ActionSupport的action类，ActionSupport类已经实现了Action和其他的一些接口
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class IntroduceAction3 extends ActionSupport {
	public String execute() throws Exception {
		System.out.println("继承了ActionSupport类");
		return NONE;
	}
}
