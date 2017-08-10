package com.qq986945193.davidsshtools.test;

import org.junit.Test;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
//演示ognl表达式
public class OgnlTest1 {

	/**
	 * 简单的测试
	 */
	@Test
	public void fun1() throws OgnlException{
		//得到上下文对象
		OgnlContext context = new OgnlContext();
		//获取到根对象
		Object root = context.getRoot();
		//存储数据
		context.put("name", "david");
		//获取值，表达式写法
		Object value = Ognl.getValue("#name", context,root);
		System.out.println(value);
	}
	
	
	/**
	 * ognl表达式中调用方法
	 */
	@Test
	public void fun2() throws OgnlException{
		//得到上下文对象
		OgnlContext context = new OgnlContext();
		//获取到根对象
		Object root = context.getRoot();
		
		//获取值，表达式写法
		Object value = Ognl.getValue("'name'.length()", context,root);
		System.out.println(value);
	}
}
