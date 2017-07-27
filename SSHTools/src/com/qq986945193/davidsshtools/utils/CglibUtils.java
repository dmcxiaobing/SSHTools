package com.qq986945193.davidsshtools.utils;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import com.qq986945193.davidsshtools.dao.SpringUserDao2;
import com.qq986945193.davidsshtools.dao.impl.SpringUserDaoImpl2;
/**
 * 使用CGLIB的方式生成代理对象
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class CglibUtils {
	/**
	 * 使用CGLIB的方式生成代理对象
	 */
	public static SpringUserDao2 getProxy() {
		Enhancer enhancer = new Enhancer();
		//设置父类
		enhancer.setSuperclass(SpringUserDaoImpl2.class);
		//设置回调函数
		enhancer.setCallback(new MethodInterceptor() {
			
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				if (method.getName().equals("save")) {
					System.out.println("记录日志");
				}
				//正常执行
				return methodProxy.invokeSuper(obj, args);
			}
		});
		//生成代理对象
		SpringUserDao2 proxy = (SpringUserDao2) enhancer.create();
		
		return proxy;
	}

}
