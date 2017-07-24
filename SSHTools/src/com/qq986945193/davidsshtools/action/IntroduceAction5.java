package com.qq986945193.davidsshtools.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.ui.Model;
import com.qq986945193.davidsshtools.domain.SpringUser2;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 关于获取到表单数据，封装数据的教程  对应的jspIntroduceActionForm.jsp
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * 
 * 属性驱动和模型驱动封装表单数据。
 * 实现ModelDriven接口 必须手动实例化对象
 */


public class IntroduceAction5 extends ActionSupport implements ModelDriven<SpringUser2>{

	private String username;
	private String password;
	private String age;

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
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

	/**
	 * 利用属性驱动的方式。需要有字符串属性和set方法，最好get和set方法都构建
	 */
	public String fun1() {
		System.out.println(username + password);
		return NONE;
	}

/**************************************************************************/
	private SpringUser2 user2;

	public SpringUser2 getUser2() {
		return user2;
	}

	public void setUser2(SpringUser2 user2) {
		this.user2 = user2;
	}

	/**
	 * 利用属性驱动的方式，现在要提供get和set方法
	 */
	public String fun2(){
		System.out.println(user2);
		return NONE;
	}
/**************************************************************************/
	/**
	 * 模型驱动的方式。实现ModelDriven接口。
	 * 必须手动实例化对象，
	 */
	//手动实例化
	private SpringUser2 springUser1 = new SpringUser2();
	/**
	 * 获取到模型对象
	 */
	@Override
	public SpringUser2 getModel() {
		return springUser1;
	}
	
	/**
	 * 获取到模型对象
	 * @return
	 */
	public String fun3(){
		System.out.println(springUser1);
		return NONE;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
}
