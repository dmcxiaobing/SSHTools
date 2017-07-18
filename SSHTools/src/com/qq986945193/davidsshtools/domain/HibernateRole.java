package com.qq986945193.davidsshtools.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试hibernate多对多的一个角色的javabean
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class HibernateRole {

	private Long rid;
	private String rname;
	//一个角色中有多个用户。
	private Set<HibernateUser> users = new HashSet<HibernateUser>();
	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public Set<HibernateUser> getUsers() {
		return users;
	}
	public void setUsers(Set<HibernateUser> users) {
		this.users = users;
	} 

	
}
