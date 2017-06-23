package com.qq986945193.davidbookstore.category.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.david.webtools.common.jdbc.TxQueryRunner;
import com.qq986945193.davidbookstore.category.domain.Category;
import com.qq986945193.davidbookstore.user.domain.User;

/**
 * 分类的持久层
 */
public class CategoryDao {

	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 查询书籍的所有分类
	 * @return
	 */
	public List<Category> findAll() {
		
		try {
			String sql = "select * from category";
			return qr.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
