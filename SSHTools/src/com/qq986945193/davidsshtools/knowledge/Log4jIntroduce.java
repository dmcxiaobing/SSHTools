package com.qq986945193.davidsshtools.knowledge;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * 简单介绍使用log4j
 */
public class Log4jIntroduce {
	//创建日志对象
	private Logger log = Logger.getLogger(Log4jIntroduce.class);
	/**
	 * 执行了
		17:18:13,685  INFO Log4jIntroduce:16 - 执行了
	 */
	@Test
	public void fun(){
		System.out.println("执行了");
		log.info("执行了");
	}
}
