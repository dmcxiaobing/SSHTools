package com.qq986945193.davidsshtools.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qq986945193.davidsshtools.constants.Api;
import com.qq986945193.davidsshtools.domain.SpringJdbcAccouont;

/**
 * 测试jdbc的模板类。
 * 
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springjdbcApplicationContext1.xml")
public class SpringJdbcTest1 {

	/**
	 * 使用模板类进行操作数据库
	 */
	@Test
	public void fun1() {
		// Spring框架提供了内置的连接池，不想使用内置的，可以整合其他的连接池。
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(Api.JDBC_CLASS_NAME_FOR_MYSQL);
		// 这个使用连接地址一定要写好
		dataSource.setUrl(Api.JDBC_URL_FOR_MYSQL);
		dataSource.setUsername(Api.JDBC_USERNAME_FOR_MYSQL);
		dataSource.setPassword(Api.JDBC_PASSWORD_FOR_MYSQL);
		// 创建模板类
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		// 设置连接池
		jdbcTemplate.setDataSource(dataSource);
		// 完成操作。
		jdbcTemplate.update("update account set balance = balance+? where username = ?", 10, "zs");
		// jdbcTemplate.update("insert into account values(?,?,?)",3,"ww",0);
	}

	/*****************************************************************************/
	// 使用注解的方式
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	/**
	 * 这里使用IOC的方式进行操作数据库 update(String sql,Object...params)可以完成增删改操作
	 */
	@Test
	public void fun2() {
		jdbcTemplate.update("update account set balance = balance+? where username = ?", 1, "zs");
	}

	/**
	 * 删除测试
	 */
	@Test
	public void fun3() {
		jdbcTemplate.update("delete from account where uid = ?", 3);
	}

	/**
	 * 测试查询；通过逐渐查询一条对象数据
	 */
	@Test
	public void fun4() {
		SpringJdbcAccouont accouont = jdbcTemplate.queryForObject("select * from account where uid = ?",
				new BeanMapper(), 1);
		System.out.println(accouont);
	}
	/**
	 * 查询所有的数据
	 */
	@Test
	public void fun5() {
		List<SpringJdbcAccouont> accouonts = jdbcTemplate.query("select * from account",new BeanMapper());
		for (SpringJdbcAccouont springJdbcAccouont : accouonts) {
			System.out.println(springJdbcAccouont);
		}
	}

}

/**
 * 自定手动的来封装数据（一行一行的封装）
 */

class BeanMapper implements RowMapper<SpringJdbcAccouont> {

	@Override
	public SpringJdbcAccouont mapRow(ResultSet rs, int rowNum) throws SQLException {
		SpringJdbcAccouont accouont = new SpringJdbcAccouont();
		accouont.setUid(rs.getInt("uid"));
		accouont.setBalance(rs.getDouble("balance"));
		accouont.setUsername(rs.getString("username"));
		return accouont;
	}

}
