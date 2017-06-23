package com.qq986945193.davidbookstore.user.dao;

import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.david.webtools.common.jdbc.TxQueryRunner;
import com.qq986945193.davidbookstore.user.domain.User;

/**
 * 业务层
 */
public class UserDao {
	private QueryRunner txQueryRunner = new TxQueryRunner();

	/**
	 * 根据用户名查询是否被注册
	 * 
	 * @param username
	 * @return
	 */
	public User findByUserName(String username) {
		try {
			String sql = "select * from tb_user where username = ?";
			return txQueryRunner.query(sql, new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		}
	}

	/**
	 * 根据email查询 数据库是否存在
	 * 
	 * @param email
	 * @return
	 */
	public User findByEmail(String email) {
		try {
			String sql = "select * from tb_user where email=?";
			return txQueryRunner.query(sql, new BeanHandler<User>(User.class),email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 注册用户保存到数据库
	 * 
	 * @param formUser
	 */
	public void regist(User formUser) {
		try {
			String sql = "insert into tb_user values (?,?,?,?,?,?)";
			Object[] params = { formUser.getUid(), formUser.getUsername(), formUser.getPassword(), formUser.getEmail(),
					formUser.getCode(), formUser.isState() };
			txQueryRunner.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据激活码查找用户信息
	 * @param code
	 */
	public User findByCode(String code) {
		try {
			String sql = "select * from tb_user where code = ?";
			return txQueryRunner.query(sql,new BeanHandler<User>(User.class),code);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据uid激活用户
	 * @param uid
	 * @param b
	 */
	public void active(String uid, boolean state) {
		try {
			String sql = "update tb_user set state = ? where uid = ?";
			txQueryRunner.update(sql,state, uid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
