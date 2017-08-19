package com.qq986945193.davidsshtools.domain;
/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
/**
 * 演示构造方法的注入方式
 */
public class SpringPerson {
	private String pname;
	private SpringCar1 car1;
	public SpringPerson(String pname, SpringCar1 car1) {
		super();
		this.pname = pname;
		this.car1 = car1;
	}
	
	public SpringPerson() {
		super();
	}

	@Override
	public String toString() {
		return "SpringPerson [pname=" + pname + ", car1=" + car1 + "]";
	}
	
}
