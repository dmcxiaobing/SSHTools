package com.qq986945193.davidbookstore.user.web.servelt;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.david.webtools.common.base.BaseServlet;
import com.david.webtools.common.utils.CommonUtils;
import com.david.webtools.common.utils.Mail;
import com.david.webtools.common.utils.MailUtils;
import com.qq986945193.davidbookstore.common.utils.MailSendUtils;
import com.qq986945193.davidbookstore.common.utils.RegexUtils;
import com.qq986945193.davidbookstore.user.domain.MailInfo;
import com.qq986945193.davidbookstore.user.domain.User;
import com.qq986945193.davidbookstore.user.service.UserException;
import com.qq986945193.davidbookstore.user.service.UserService;

/**
 * 用户的控制器
 */
public class UserServlet extends BaseServlet {
	private UserService userService = new UserService();

	/*
	 * 注册功能
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * 1. 封装表单数据到form对象中 2. 补全：uid、code 3. 输入校验 >
		 * 保存错误信息、form到request域，转发到regist.jsp 4. 调用service方法完成注册 >
		 * 保存错误信息、form到request域，转发到regist.jsp 5. 发邮件 6. 保存成功信息转发到msg.jsp
		 */
		// 封装表单数据
		User formUser = CommonUtils.toBean(request.getParameterMap(), User.class);
		// 补全激活码和Uid
		formUser.setUid(CommonUtils.getUUIDRandomNum());
		formUser.setCode(CommonUtils.getUUIDRandomNum() + CommonUtils.getUUIDRandomNum());
		/**
		 * 校验是否输入的用户名和密码符合要求 1. 创建一个Map，用来封装错误信息，其中key为表单字段名称，值为错误信息
		 * errors:是用户名和密码的错误信息，msg是操作失败的信息
		 */
		Map<String, String> errors = new HashMap<String, String>();
		/*
		 * 2. 获取form中的username、password、email进行校验
		 */
		if (formUser.getUsername() == null || formUser.getUsername().isEmpty()) {
			errors.put("username", "用户名不能为空");
		} else if (formUser.getUsername().length() < 3 || formUser.getUsername().length() > 10) {
			errors.put("username", "用户名长度必须是3到10位");
		}
		String password = formUser.getPassword();
		if (password == null || password.isEmpty()) {
			errors.put("password", "密码不能为空");
		} else if (password.length() < 3 || password.length() > 10) {
			errors.put("password", "密码长度不正确");
		}
		String email = formUser.getEmail();
		if (email == null || email.trim().isEmpty()) {
			errors.put("email", "Email不能为空！");
		} else if (!RegexUtils.checkEmail(email)) {
			errors.put("email", "Email格式错误！");
		}
		/**
		 * 如果map不为空，则说明有错误，则转发到regist.jsp
		 */
		if (errors.size() > 0) {
			/**
			 * 保存错误信息，保存表单数据，转发到regist.jsp
			 */
			request.setAttribute("errors", errors);
			request.setAttribute("form", formUser);
			return "f:/jsps/user/regist.jsp";
		}
		/**
		 * 如果没有错误，则需要注册到数据库
		 */
		try {
			userService.regist(formUser);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", formUser);
			return "f:/jsps/user/regist.jsp";
		}
		/**
		 * 注册成功之后，发送邮件。
		 */
		sendMail(formUser);
//		sendCodeMail(formUser);
		/**
		 * 保存成功信息 转发到msg.jsp
		 */
		request.setAttribute("msg", "恭喜您注册成功，请到邮件进行激活");
		return "f:/jsps/msg.jsp";
	}
	
	/**
	 * 激活账户
	 */
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");
		/**
		 * 保存成功信息 转发到msg.jsp
		 */
		try {
			userService.active(code);
			request.setAttribute("msg", "恭喜您激活成功");
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
		}
		
		return "f:/jsps/msg.jsp";
	}
	/**
	 * 登陆账户
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/**
		 * 1，首先要查询用户名是否存在，然后在根据用户名查看密码是否一致
		 */
		User userform = CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			User user = userService.login(userform);
			request.getSession().setAttribute("session_user", user);
			return "r:/index.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", userform);
			return "f:/jsps/user/login.jsp";
		}
	}
	/**
	 * 退出用户功能
	 */
	public String quit(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.getSession().invalidate();
		return "r:/index.jsp";
	}
	
	/**
	 * 发送邮件
	 * @param formUser
	 * @throws Exception
	 */
	private void sendCodeMail(User formUser) throws Exception {
		MailInfo info = new MailInfo();
		info.setHost("smtp.163.com");
		info.setFormName("dmcxiaobing@163.com");
		info.setFormPassword("password");
		info.setReplayAddress("dmcxiaobing@163.com");
		info.setToAddress(formUser.getEmail());
		info.setSubject("bbs测试邮件");
		info.setContent("这是一封测试邮件");
		MailSendUtils.sendTextMail(info);
	}
	/**
	 * 发送邮件
	 */
	private void sendMail(User formUser) {
		// 获取配置文件内容
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
			String host = properties.getProperty("host");// 获取服务器主机
			String uname = properties.getProperty("uname");// 获取用户名
			String pwd = properties.getProperty("pwd");// 获取密码
			String from = properties.getProperty("from");// 获取发件人
			String subject = properties.getProperty("subject");// 获取主题
			String toEmail = formUser.getEmail();// 获取收件人
			String content = properties.getProperty("content");// 获取邮件内容
			content = MessageFormat.format(content, formUser.getCode());// 替换占位符{0}
			/**
			 * 发送邮件
			 */
			// 得到session
			Session session = MailUtils.createSession(host, uname, pwd);
			// 创建邮件对象
			Mail mail = new Mail(from, toEmail, subject, content);
			// 发邮件
			MailUtils.send(session, mail);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
