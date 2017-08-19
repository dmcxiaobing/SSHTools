package com.qq986945193.davidsshtools.domain;
/**
 * 利用驱动的方式，获取到用户数据，封装到javabean。
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class SpringUser2 {
	private String username;
	private String password;
	private String age;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "SpringUser2 [username=" + username + ", password=" + password + ", age=" + age + "]";
	}
	
}
