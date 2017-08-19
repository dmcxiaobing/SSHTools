/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */

package com.qq986945193.davidsshtools.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 一个用来演示hibernate注解javabean的功能
 * @Transient 不将此字段加入到数据库表
	@Entity　　--->　　如果我们当前这个bean要设置成实体对象，就需要加上Entity这个注解
	@Table(name="hibernate_anno_user")　　---->　　设置数据库的表名
	  @Id　　--->　　定义为数据库的主键ID　　(建议不要在属性上引入注解，因为属性是private的，如果引入注解会破坏其封装特性，所以建议在getter方法上加入注解)
    @GeneratedValue　　---->　　ID的生成策略为自动生成　　
    @Column(name="register_date")　　--->　　Column中的name属性对应了数据库的该字段名字，里面还有其他属性，例如length，nullable等等
 *
 */
@Table
@Entity(name="hibernate_anno_user")
public class HibernateAnnotationUser {

	private Integer uid;
	private String username;
	private String password;
	private String ignore;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="uid")
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Transient
	public String getIgnore() {
		return ignore;
	}
	public void setIgnore(String ignore) {
		this.ignore = ignore;
	}
	
	
}
