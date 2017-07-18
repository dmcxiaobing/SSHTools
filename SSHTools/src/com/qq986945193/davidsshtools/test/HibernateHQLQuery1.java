package com.qq986945193.davidsshtools.test;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.qq986945193.davidsshtools.domain.Customer;
import com.qq986945193.davidsshtools.domain.HibernateCustomer;
import com.qq986945193.davidsshtools.domain.Linkman;
import com.qq986945193.davidsshtools.utils.HibernateUtils;


/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * 对象导航。Hibernate查询  HQL Hibernate query language
 * 分页查询：第几条记录=(当前页-1)*每页记录数 如（2-1）*10 第二页
 */
public class HibernateHQLQuery1 {

	/**
	 * 对象导航的方式
	 */
	@Test
	public void fun1() {
		//先查询客户。
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//根据UID查询客户
		HibernateCustomer customer = session.get(HibernateCustomer.class,28L);
		System.out.println(customer);
		tr.commit();
	}
	
/*******************************************************************************************/
	/**
	 * 演示hql查询 基本的查询
	 */
	@Test
	public void HQL1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//创建HQL的查询接口
		Query query = session.createQuery("from Customer");
		//调用list()方法，查询
		List<Customer> lists = query.list();
		for (Customer customer : lists) {
			System.out.println(customer);
		}
		tr.commit();
	}
	
	/**
	 * HQL支持方法链的编程风格
	 */
	@Test
	public void HQL2() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		List<Customer> lists = session.createQuery("from Customer").list();
		
		for (Customer customer : lists) {
			System.out.println(customer);
		}
		tr.commit();
	}
	
	/**
	 * HQL利用有别名的查询方式
	 * 正确语法：select c from customer c
	 * 错误语法：select * from Customer 	
	 */
	@Test
	public void HQL3(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		List<Customer> lists = session.createQuery("select c from Customer c").list();
		for (Customer customer : lists) {
			System.out.println(customer);
		}
		
		tr.commit();
	}
	
	/**
	 * HQL排序查询  
	 * SQL:order by 字段 asc/desc  asc升序，desc降序
	 * HQL：关键词是一样的，都有order by属性
	 */
	@Test
	public void HQL4(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		//查询联系人
		List<Customer> lists = session.createQuery("from Customer c order by c.cust_id desc").list();
		for (Customer customer : lists) {
			System.out.println(customer);
		}
				
		tr.commit();
	}
	
	/**
	 * HQL分页查询。
	 * 有两个方法 setFirstResult(a);//从哪条记录开始，如果查询是从第一条开始，则值为0
	 * 		setMaxResult(b);//每页查询的记录数
	 */
	@Test
	public void HQL5(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		//查询联系人
		Query query = session.createQuery("from Customer");
		//第几条记录=(当前页-1)*每页记录数 如（2-1）*10 第二页
		query.setFirstResult(0);
		query.setMaxResults(3);
		//查询第二页的数据为 （2-1）*3
//		query.setFirstResult(3);
//		query.setMaxResults(3);
		List<Customer> lists = query.list();
		for (Customer customer : lists) {
			System.out.println(customer);
		}
		tr.commit();
	}
	/**
	 * HQL按条件查询
	 */
	@Test
	public void HQL6(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//按条件查询联系人
		Query query = session.createQuery("from Customer c where c.cust_id > ? and c.cust_id < ?");
		/**
		 * 首先这里使用一般的方法进行设置.但是我们需要知道类型。下面使用通用的方法，则不需要判断类型
		 */
//		query.setInteger(0, 10);
//		query.setInteger(1, 22);
		//通用的方法，将不再判断具体的类型
		query.setParameter(0, 10L);
		query.setParameter(1, 22L);
		List<Customer> list = query.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		tr.commit();
	}
	
	/**
	 * HQL投影查询，只查询几个字段，不是所有的字段
	 */
	@Test
	public void HQL7() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//查询联系人
		Query query = session.createQuery("select lkm_name,lkm_gender from Linkman");
		List<Object[]> list = query.list();
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}
		tr.commit();
	}
	
	/**
	 * HQL投影查询，只查询几个字段，不是所有的字段
	 * 第一步：需要在JavaBean类提供对应的构造方法，记得再添加无参的构造方法
	 * 第二部：HQL语句的发生变化好HQL7方法是不同的
	 */
	@Test
	public void HQL8() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//查询联系人
		Query query = session.createQuery("select new Linkman(lkm_name,lkm_gender) from Linkman");
		List<Linkman> list = query.list();
		for (Linkman linkman : list) {
			System.out.println(linkman);
		}
		tr.commit();
	}
	
	/**
	 * HQL之聚合函数：count(),sum(),avg(),max(),min()
	 */
	@Test
	public void HQL9() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//查询联系人的所有数量
		Query query = session.createQuery("select count(*) from Linkman");
		List<Number> list = query.list();
		//通过下标取值 得到Number类型的值，然后可以任意转换double或者int等类型
		Long count = list.get(0).longValue();
		System.out.println("所有数量:"+count);
		
		tr.commit();
	}
	/**
	 * HQL之聚合函数：count(),sum(),avg(),max(),min()
	 */
	@Test
	public void HQL10() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//查询联系人的所有数量
		//Query query = session.createQuery("select count(l) from Linkman l");
		//求总和。。
		Query query = session.createQuery("select sum(lkm_id) from Linkman l");
		List<Number> list = query.list();
		//通过下标取值 得到Number类型的值，然后可以任意转换double或者int等类型
		Long count = list.get(0).longValue();
		System.out.println("所有数量:"+count);
		
		tr.commit();
	}
	
	
	
	
	
	
	
}
