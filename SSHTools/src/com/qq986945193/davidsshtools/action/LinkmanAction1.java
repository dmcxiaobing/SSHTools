package com.qq986945193.davidsshtools.action;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 编写的客户的Action的类 演示通配符方式。具体请看struts.xml配置文件
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class LinkmanAction1 extends ActionSupport {
	
	//保存客户
	public String save(){
		System.out.println("保存客户LinkmanAction1");
		return "saveOk";
	}
	
	
	//删除客户
	public String delete(){
		System.out.println("删除客户LinkmanAction1");
		return "deleteOk";
	}
}
