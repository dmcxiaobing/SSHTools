package com.qq986945193.davidsshtools.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试多对多的一个用户实体类
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class HibernateUser {
	private Long uid;
	private String username;
	private String password;
	//一个用户中有多个角色。所以用set集合。
	private Set<HibernateRole> roles = new HashSet<HibernateRole>();
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
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
	public Set<HibernateRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<HibernateRole> roles) {
		this.roles = roles;
	}

}
