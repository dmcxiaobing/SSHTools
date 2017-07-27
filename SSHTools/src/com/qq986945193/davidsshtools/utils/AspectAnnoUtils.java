package com.qq986945193.davidsshtools.utils;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 使用注解方式的切面类
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@Aspect
public class AspectAnnoUtils {
	
	/**
	 * 通知类型：@Before前置通知，相当于（切入点的表达式）
	 */
	//@Before(value="AspectAnnoUtils.fn()")
	public void log(){
		System.out.println("记录日志");
	}
}
