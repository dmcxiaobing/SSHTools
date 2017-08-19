package com.qq986945193.davidsshtools.domain;
/**
 * 采用set方法演示注入的方式
 */
public class SpringCar2 {
	private String cname;
	private Double price;
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "SpringCar2 [cname=" + cname + ", price=" + price + "]";
	}
	
}
