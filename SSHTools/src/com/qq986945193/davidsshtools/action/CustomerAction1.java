package com.qq986945193.davidsshtools.action;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 编写的客户的Action的类 演示传统方式具体请看struts.xml配置文件
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class CustomerAction1 extends ActionSupport {
	
	//保存客户
	public String save(){
		System.out.println("保存客户CustomerAction1");
		return NONE;
	}
	
	
	//删除客户
	public String delete(){
		System.out.println("删除客户CustomerAction1");
		return NONE;
	}
}
