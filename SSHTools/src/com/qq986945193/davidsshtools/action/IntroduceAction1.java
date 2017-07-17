package com.qq986945193.davidsshtools.action;
/**
 * Struts2框架的action类处理的简单请求介绍
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
//POJO类：没有任何继承和实现
public class IntroduceAction1 {
	
	
	/**
	 * 演示的method方法的默认值,return null 不会进行跳转
	 * <action name="hello" class="com.qq986945193.davidsshtools.action.IntroduceAction1" >
	 * (http://localhost/SSHTools/hello.action)
	 */
	public String execute(){
		System.out.println("..方法的默认值是execute");
		return null;
	}
	
	/**
	 * Action类中的方法格式有强制要求，必须如下：
	 * public 是公有的，
	 * 必须有返回值，而且必须是String类型
	 * 方法名称可以是任意的，但是不能有参数列表
	 * 页面的跳转：
	 * 1,return:"字符串"
	 * 2,需要在struts.xml配置文件中，配置跳转的页面
	 */
	public String sayHello(){
		//编写代码，接受请求的参数
		System.out.println("sayhello执行了");
		return "ok";
	}
	
}
