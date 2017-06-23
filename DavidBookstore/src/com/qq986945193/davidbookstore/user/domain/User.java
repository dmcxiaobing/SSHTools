package com.qq986945193.davidbookstore.user.domain;
/**
 * 用户的javabean
 */
public class User {
	/**
	 * 对应数据库的表
	 */
	private String uid;//主键用户id
	private String username;//用户名
	private String password;//密码
	private String email;//邮箱
	private String code;//激活码
	private boolean state;//激活状态 是否激活
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
	
}
