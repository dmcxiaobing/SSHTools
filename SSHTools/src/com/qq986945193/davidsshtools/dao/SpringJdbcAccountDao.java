package com.qq986945193.davidsshtools.dao;
/**
 * 这是一个转账的功能，测试jdbc事务
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public interface SpringJdbcAccountDao {
	/**
	 * 扣钱
	 */
	public abstract  void outMoney(String outName,double money);
	//加钱
	public abstract void inMoney(String inName,double money);
}
