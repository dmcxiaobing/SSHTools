package com.qq986945193.davidsshtools.service.impl;

import javax.annotation.Resource;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.qq986945193.davidsshtools.dao.SpringJdbcAccountDao;
import com.qq986945193.davidsshtools.service.SpringJdbcAccountService1;

/**
 * 转账的功能
 * 
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@Transactional
public class SpringJdbcAccountServiceImpl1 implements SpringJdbcAccountService1 {

	// 注入持久层和事务的模板类
	private SpringJdbcAccountDao accountDao;

	private TransactionTemplate transactionTemplate;

	public SpringJdbcAccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(SpringJdbcAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * 手动编写事务
	 */
	@Override
	public void pay(final String out, final String in, final int money) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			// 事务的执行，如果没有问题，提交，如果出现了异常，回滚事务
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				// 调用扣钱的方法
				accountDao.outMoney(out, money);
				// int num = 10/0;
				// 调用加钱的方法
				accountDao.inMoney(in, money);
			}
		});
	}

	/**
	 * 交给aop进行管理
	 */
	@Override
	public void pay2(String out, String in, int money) {
		// 先扣钱
		accountDao.outMoney(out, money);
		// 模拟异常
		int a = 10 / 0;
		// 加钱
		accountDao.inMoney(in, money);
	}

	/**
	 * 使用注解的方式进行管理事务
	 */
	@Override
	public void pay3(String out, String in, int money) {
		// 先扣钱
		accountDao.outMoney(out, money);
		// 模拟异常
		//int a = 10 / 0;
		// 加钱
		accountDao.inMoney(in, money);

	}

}
