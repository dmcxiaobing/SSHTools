package com.qq986945193.davidbookstore.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.david.webtools.common.base.BaseServlet;
import com.qq986945193.davidbookstore.book.service.BookService;
/**
 * 书籍的控制器
 */
public class BookServlet extends BaseServlet {

	private BookService bookService = new BookService();
	/**
	 * 根据cid分类查询书籍
	 */
	public String findByCategory(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		request.setAttribute("bookList", bookService.findBookByCid(cid));
		return "/jsps/book/list.jsp";
	}
	
	/**
	 * 查询所有图书
	 * @return
	 */
	public String findAllBook(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		request.setAttribute("bookList", bookService.findAllBook());
		return "/jsps/book/list.jsp";
	}
	
	/**
	 * 根据bid查询书籍的详情
	 */
	public String loadByBid(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String bid = request.getParameter("bid");
		request.setAttribute("book", bookService.loadByBid(bid));
		return "f:/jsps/book/desc.jsp";
	}
}
