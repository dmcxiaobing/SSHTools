package com.qq986945193.davidsshtools.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.david.webtools.common.utils.CommonUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.qq986945193.davidsshtools.domain.User;
import com.qq986945193.davidsshtools.domain.UserActionBean;
import com.qq986945193.davidsshtools.service.UserService;
/**
 * 用户模块的controller
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class UserAction extends ActionSupport {

	private UserService userService = new UserService();
	/**
	 * 登陆的处理
	 */
	public String login(){
		System.out.println("没进来吗？");
		//得到HttpServletRequest对象
		HttpServletRequest request = ServletActionContext.getRequest();
		//获取请求参数
		UserActionBean userform = CommonUtils.toBean(request.getParameterMap(), UserActionBean.class);
		System.out.println(userform);
		if (userform != null && userform.getUsername()!=null && userform.getPassword()!=null 
				&&!userform.getUsername().trim().isEmpty()
				&&!userform.getPassword().trim().isEmpty()) {
			//调用业务层，登陆。
			UserActionBean user = userService.login(userform);
			if (user == null) {
				//如果user等于null，则说明用户名或者密码错误，或者不存在此用户
				return LOGIN;
			}else {
				//如果存在，则将用户信息保存到session域中
				request.getSession().setAttribute("session_user", user);
				return SUCCESS;
			}	
		}else {
			return LOGIN;
		}
		
	}
}
