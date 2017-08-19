package com.qq986945193.davidsshtools.service;

/**
 * 转账的示例
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public interface SpringJdbcAccountService1 {
	/**
	 * 转账
	 */
	public void pay(String string, String string2, int i);

	/**
	 * 直接xml中管理事务，配置AOP
	 */
	public void pay2(String string, String string2, int i);
	/**
	 * 使用注解进行管理事务
	 */
	public void pay3(String string, String string2, int i);
}
