package com.qq986945193.davidsshtools.domain;
/**
 * 一个测试金额的Javabean
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class SpringJdbcAccouont {
	
	private Integer uid;
	private String username;
	private Double balance;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "SpringJdbcAccouont [uid=" + uid + ", username=" + username + ", balance=" + balance + "]";
	}
	
}
