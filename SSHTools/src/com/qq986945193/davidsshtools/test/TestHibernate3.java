package com.qq986945193.davidsshtools.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.qq986945193.davidsshtools.domain.HibernateCustomer;
import com.qq986945193.davidsshtools.domain.Linkman;
import com.qq986945193.davidsshtools.utils.HibernateUtils;

/**
 * 测试一对多的功能
 * 
 *   * 级联保存：保存一方同时可以把关联的对象也保存到数据库中！！
    * 使用cascade="save-update"
    * 1. cascade用来级联操作（保存、修改和删除）
		2. inverse用来维护外键的
		
		 * none  -- 不使用级联
    * save-update   -- 级联保存或更新
    * delete       -- 级联删除
    * delete-orphan     -- 孤儿删除.(注意：只能应用在一对多关系)
    * all      -- 除了delete-orphan的所有情况.（包含save-update delete）
    * all-delete-orphan   -- 包含了delete-orphan的所有情况.（包含save-update delete delete-orphan）
 */
public class TestHibernate3 {
	/**
	 * 双向关联的方式，保存数据
	 */
	@Test
	public void fun1(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//保存客户和联系人的数据
		//创建一个客户
		HibernateCustomer hibernateCustomer = new HibernateCustomer();
		hibernateCustomer.setCust_name("客户Name");
		
		//创建两个联系人
		Linkman linkman1 = new Linkman();
		linkman1.setLkm_name("双向关联系人1");
		Linkman linkman2 = new Linkman();
		linkman2.setLkm_name("双向关联系人2");

		//演示双向关联
		hibernateCustomer.getLinkmans().add(linkman1);
		hibernateCustomer.getLinkmans().add(linkman2);
		
		linkman1.setCustomer(hibernateCustomer);
		linkman2.setCustomer(hibernateCustomer);
		
		//保存数据
		session.save(hibernateCustomer);
		session.save(linkman1);
		session.save(linkman2);
		transaction.commit();
	}
	/**
	 * 单向的关联，如果不配置级联保存，程序会出现异常
	 *  * 级联保存：保存一方同时可以把关联的对象也保存到数据库中！！
    * 使用cascade="save-update"
    * 1. cascade用来级联操作（保存、修改和删除）
		2. inverse用来维护外键的
		
		单向级联保存。客户表和联系人表都有数据，inverse="false" 如果为false则会关联，否则则放弃外键维护，这样就不会关联
	 */
	@Test
	public void fun2(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//这个单向保存，
		HibernateCustomer customer = new HibernateCustomer();
		customer.setCust_name("客户张1");
	
		//创建两个联系人
		Linkman linkman1 = new Linkman();
		linkman1.setLkm_name("单向联系人张11");
		Linkman linkman2 = new Linkman();
		linkman2.setLkm_name("单向联系人张21");

		//单向关联
		customer.getLinkmans().add(linkman1);
		customer.getLinkmans().add(linkman2);
		session.save(customer);
		tr.commit();
	}
	/**
	 * 级联保存，保存客户，级联：联系人  cascade用来级联操作（保存、修改和删除）
	 */
	@Test
	public void fun3(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//保存客户和联系人的数据 级联联系人
		HibernateCustomer customer = new HibernateCustomer();
		customer.setCust_name("客户4");
	
		//创建两个联系人
		Linkman linkman1 = new Linkman();
		linkman1.setLkm_name("单向联系人4");
		Linkman linkman2 = new Linkman();
		linkman2.setLkm_name("单向联系人4");

		//单向关联
		customer.getLinkmans().add(linkman1);
		customer.getLinkmans().add(linkman2);
		session.save(customer);
		tr.commit();
	}
	
	
	/**
	 * 级联保存，保存联系人，级联：客户 cascade用来级联操作（保存、修改和删除）
	 * 如果用联系人多方进行级联保存，需要 cascade="save-update" 配置在联系人方
	 */
	@Test
	public void fun4(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//保存客户和联系人的数据 
		HibernateCustomer customer = new HibernateCustomer();
		customer.setCust_name("客户3");
	
		//创建两个联系人
		Linkman linkman1 = new Linkman();
		linkman1.setLkm_name("单向联系人3");
		Linkman linkman2 = new Linkman();
		linkman2.setLkm_name("单向联系人3");

		//使用联系人关联客户
		linkman1.setCustomer(customer);
		linkman2.setCustomer(customer);
		//保存联系人
		session.save(linkman1);
		session.save(linkman2);
		tr.commit();
	}	
	/**
	 * 测试级联保存  熊大 有外键，熊二没有  这里因为是级联保存，所以双方都要配置：	cascade="save-update"
	 * 由于熊大有外键，所以是从联系人表级联保存，所以客户表放弃外键维护这样熊二才没有外键
	 */
	@Test
	public void fun5(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//保存客户和联系人的数据 
		HibernateCustomer customer = new HibernateCustomer();
		customer.setCust_name("david");

		//创建两个联系人
		Linkman linkman1 = new Linkman();
		linkman1.setLkm_name("熊大");
		Linkman linkman2 = new Linkman();
		linkman2.setLkm_name("熊二");
		
		//联系人关联客户和客户关联联系人
		linkman1.setCustomer(customer);
		customer.getLinkmans().add(linkman2);
		session.save(linkman1);
		tr.commit();
	}
	
	
	/**
	 * 测试：删除客户，客户下有2个联系人inverse="false" name="linkmans" sort="unsorted" cascade="delete"
	 * 如果改为save-update则不会删除
	 * 测试级联删除，删除客户，级联删除客户下的联系人
	 * 
	 * none                  -- 不使用级联
    * save-update               -- 级联保存或更新
    * delete                    -- 级联删除
    * delete-orphan             -- 孤儿删除.(注意：只能应用在一对多关系)
    * all                       -- 除了delete-orphan的所有情况.（包含save-update delete）
    * all-delete-orphan         -- 包含了delete-orphan的所有情况.（包含save-update delete delete-orphan）
	 */
	@Test
	public void fun6(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 先查询1号客户
		HibernateCustomer customer = session.get(HibernateCustomer.class, 45L);
		session.delete(customer);
		
		tr.commit();
	}
	
	/**
	 * 删除联系人，级联删除客户  配置对应关系****************
	 */
	@Test
	public void fun7(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Linkman man = session.get(Linkman.class, 10L);
		session.delete(man);
		tr.commit();
	}
	
	/**
	 * 解除关系：从集合中删除联系人.将联系人删除
	 * one-to-many cascade="all-delete-orphan " 删除联系人
	 */
	@Test
	public void fun8(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		// 先获取到客户
		HibernateCustomer c1 = session.get(HibernateCustomer.class, 43L);
		Linkman l1 = session.get(Linkman.class, 9L);
		System.out.println(c1);
		// 解除
		c1.getLinkmans().remove(l1);
		tr.commit();
	}
	/**
	 * 更改外间的维护，让熊大联系人属于david客户	
	 *one-to-many cascade="all-delete-orphan "
	 */
	@Test
	public void fun9(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		//先获取到david的客户
		HibernateCustomer david = session.get(HibernateCustomer.class, 43L);
		//获取到熊大联系人
		Linkman l1 = session.get(Linkman.class, 16L);
		//做双向的关联
		david.getLinkmans().add(l1);
		l1.setCustomer(david);
		//修改提交
		tr.commit();
	}
	
	/**
	 * cascade 是操作外键。和inverse是维护外键。的区别
	 */
	@Test
	public void fun10(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		// 级联保存
		HibernateCustomer c1 = new HibernateCustomer();
		c1.setCust_name("美美");
		
		// 创建2个联系人
		Linkman l1 = new Linkman();
		l1.setLkm_name("熊大");
		Linkman l2 = new Linkman();
		l2.setLkm_name("熊二");
		
		l1.setCustomer(c1);
		l2.setCustomer(c1);
		
		session.save(l1);
		session.save(l2);
		
		// 不用修改
		tr.commit();
	}
}
