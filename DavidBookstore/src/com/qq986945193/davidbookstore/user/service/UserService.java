package com.qq986945193.davidbookstore.user.service;

import com.qq986945193.davidbookstore.user.dao.UserDao;
import com.qq986945193.davidbookstore.user.domain.User;

/**
 * user的业务层
 */
public class UserService {

	private UserDao userDao = new UserDao();

	/**
	 * 注册
	 * 
	 * @param formUser
	 */
	public void regist(User formUser) throws UserException {
		// 先检查用户名是否被注册
		User user = userDao.findByUserName(formUser.getUsername());
		if (user != null) {
			throw new UserException("用户名已经被注册");
		}
		System.out.println(formUser.getEmail());
		user = userDao.findByEmail(formUser.getEmail());
		if (user != null) {
			throw new UserException("邮箱已经被注册");
		}
		// 插入用户到数据库
		userDao.regist(formUser);

	}

	/**
	 * 激活账号
	 * 
	 * @param code
	 * @throws Exception
	 */
	public void active(String code) throws Exception {
		/**
		 * 首先判断激活码是否正确，然后查看是否是激活状态，若是没有激活，方可调用激活方法
		 */
		User user = userDao.findByCode(code);
		if (user == null) {
			throw new UserException("激活码错误");
		}
		if (user.isState()) {
			throw new UserException("您已经激活过了，不要再激活了");
		}
		/**
		 * 修改用户的状态
		 */
		userDao.active(user.getUid(), true);
	}

	/**
	 * 登陆
	 */
	public User login(User userform) throws Exception {
		/**
		 * 1，首先要查询用户名是否存在，然后在根据用户名查看密码是否一致
		 */
		User user = userDao.findByUserName(userform.getUsername());
		if (user == null) {
			throw new RuntimeException("用户名不存在");
		}
		if (!userform.getPassword().equals(user.getPassword())) {
			throw new RuntimeException("密码不正确");
		}
		if (!user.isState()) {
			throw new RuntimeException("请先激活用户");
		}
		return user;
	}

}
