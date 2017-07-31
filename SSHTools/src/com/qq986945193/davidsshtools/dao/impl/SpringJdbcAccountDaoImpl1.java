package com.qq986945193.davidsshtools.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.qq986945193.davidsshtools.dao.SpringJdbcAccountDao;
/**
 * 进行转账的示例，来测试jdbc的事务
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class SpringJdbcAccountDaoImpl1 extends JdbcDaoSupport implements SpringJdbcAccountDao {

	//通过继承JDBCDaoSupport 获取到JdbcTemplate
	@Override
	public void outMoney(String outName, double money) {
		this.getJdbcTemplate().update("update account set balance = balance-? where username = ?",money,outName);
	}

	@Override
	public void inMoney(String inName, double money) {
		this.getJdbcTemplate().update("update account set balance = balance+? where username = ?",money,inName);
	}

}
