package com.qq986945193.davidsshtools.domain;

/**
 * 用来演示构造方法的注入的方式
 */
public class SpringCar1 {
	private String cname;
	private Double price;

	public SpringCar1(String cname, Double price) {
		super();
		this.cname = cname;
		this.price = price;
	}

	@Override
	public String toString() {
		return "SpringCar1 [cname=" + cname + ", price=" + price + "]";
	}

	public SpringCar1() {
		super();
	}
	
}
