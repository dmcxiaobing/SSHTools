package com.qq986945193.davidsshtools.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 使用注解方式的切面类
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@Aspect
public class AspectAnnoUtils {
	
	/**
	 * 自定义一个切入点
	 */
	@Pointcut(value = "execution(public * com.qq986945193.davidsshtools.dao.impl.SpringUserDaoImpl2.save())")
	public void fn(){
		
	}
	
	/**
	 * 通知类型：@Before前置通知，相当于（切入点的表达式）.
	 * 当然我们也可以进行抽取出来。共用一个切入点
	 */
	@Before(value="AspectAnnoUtils.fn()")
	//@Before(value="execution(public * com.qq986945193.davidsshtools.dao.impl.SpringUserDaoImpl2.save())")
	public void log(){
	//	System.out.println("记录日志");
	}
	
	/**
	 * 最终通知
	 */
	@After(value = "AspectAnnoUtils.fn()")
	public void after(){
//		System.out.println("最终通知");
	}

	
	/**
	 * 环绕通知
	 */
	@Around(value = "AspectAnnoUtils.fn()")
	public void around(ProceedingJoinPoint joinPoint){
		System.err.println("环绕通知a");
		try {
			//让目标对象的方法执行
			joinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("环绕通知b");
		
	}
	
}
