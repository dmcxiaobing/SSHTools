package com.qq986945193.davidbookstore.book.service;

import java.util.List;

import com.qq986945193.davidbookstore.book.dao.BookDao;
import com.qq986945193.davidbookstore.book.domain.Book;

/**
 * 书籍的业务层
 */
public class BookService {

	public BookDao bookDao = new BookDao();
	/**
	 * 根据cid查询图书
	 * @param cid
	 * @return
	 */
	public List<Book> findBookByCid(String cid) {
		// TODO Auto-generated method stub
		return bookDao.findBookByCid(cid);
	}
	/**
	 * 查询所有图书
	 */
	public List<Book> findAllBook() {
		
		return bookDao.findAllBook();
	}
	/**
	 * 根据bid查询book的详情
	 */
	public Book loadByBid(String bid) {
		return bookDao.loadByBid(bid);
	}
}
