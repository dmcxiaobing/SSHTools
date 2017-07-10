package com.qq986945193.davidsshtools.service;

/**
 * uservice的实现类
 */
public class SpringUserServiceImpl implements SpringUserService{
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void sayHello() {
		System.out.println("hello spring"+name);
	}

	/**
	 * 初始化
	 */
	public void init(){
		System.out.println("初始化");
	}
	/**
	 * 销毁
	 */
	public void destory(){
		System.out.println("销毁");
	}
}
