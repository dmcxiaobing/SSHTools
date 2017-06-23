package com.qq986945193.davidbookstore.book.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.david.webtools.common.jdbc.TxQueryRunner;
import com.qq986945193.davidbookstore.book.domain.Book;

/**
 * 书籍的持久层
 */
public class BookDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 根据cid查询图书
	 * 
	 * @param cid
	 * @return
	 */
	public List<Book> findBookByCid(String cid) {
		try {
			String sql = "select * from book where cid = ?";
			return qr.query(sql, new BeanListHandler<Book>(Book.class), cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查询所有图书
	 */
	public List<Book> findAllBook() {
		try {
			String sql = "select * from book";
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 根据bid查询书籍的详情
	 * @param bid
	 * @return
	 */
	public Book loadByBid(String bid) {
		try {
			String sql = "select * from book where bid = ?";
			return qr.query(sql, new BeanHandler<Book>(Book.class),bid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
