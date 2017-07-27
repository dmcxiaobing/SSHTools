package com.qq986945193.davidsshtools.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.qq986945193.davidsshtools.dao.SpringUserDao2;

/**
 * 使用JDK的方式生成代理对象
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class JDKProxyUtils {

	/**
	 * 获取到代理。 这里进行记录日志
	 */
	public static SpringUserDao2 getProxy(final SpringUserDao2 userDao2){
		//使用Proxy类生成代理对象
		SpringUserDao2 proxy = (SpringUserDao2) Proxy.newProxyInstance(userDao2.getClass().getClassLoader(), userDao2.getClass().getInterfaces(), new InvocationHandler() {
			//代理对象方法一执行，invoke方法就会执行一次
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//得到方法如果是save则记录日志。当然我们可以开启事务或者之类的
				if ("save".equals(method.getName())) {
					System.out.println("记录日志");
					//开启事务
				}
				//然后我们就可以提交事务了。
				//最后让dao类的save或者update方法正常的执行下去
				
				return method.invoke(userDao2, args);
			}
		});
		//返回代理对象
		return proxy;
	}
}
