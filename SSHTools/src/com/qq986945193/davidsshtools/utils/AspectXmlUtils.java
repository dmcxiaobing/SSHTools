package com.qq986945193.davidsshtools.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 这是一个切面类。包含：切入点和通知
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class AspectXmlUtils {
	
	/**
	 * 通知（具体的增强） 
	 */
	public void log(){
		System.out.println("记录日志");
	}
	
	/**
	 * 最终通知，方法执行陈宫或者出现异常，都会执行
	 */
	public void after(){
		System.out.println("最终通知。。。");
	}
	
	/**
	 * 方法执行之后，执行后置通知。如果程序出现了异常，则后置通知不会执行的
	 */
	public void afterReturn(){
		System.out.println("后置通知..");
	}
	
	/**
	 * 环绕通知：方法执行之前和方法执行之后进行通知。默认的情况下，目标对象的方法不能执行，需要手动让目标执行
	 */
	public void around(ProceedingJoinPoint joinPoint){
		System.out.println("环绕通知1。。。");
		//手动让目标对象方法执行
		try {
			joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("环绕通知2.。。");
	}
}
