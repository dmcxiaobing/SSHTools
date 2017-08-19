package com.qq986945193.davidsshtools.action;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 编写的客户的Action的类 演示动态方法访问方式。具体请看struts.xml配置文件
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class UserAction1 extends ActionSupport {
	
	//保存客户
	public String save(){
		System.out.println("保存客户UserAction1");
		return NONE;
	}
	
	
	//删除客户
	public String delete(){
		System.out.println("删除客户UserAction1");
		return NONE;
	}
}
