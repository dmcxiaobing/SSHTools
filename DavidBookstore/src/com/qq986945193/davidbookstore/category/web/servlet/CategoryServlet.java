package com.qq986945193.davidbookstore.category.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.david.webtools.common.base.BaseServlet;
import com.qq986945193.davidbookstore.category.service.CategoryService;
/**
 * 分类的servlet控制器
 */
public class CategoryServlet extends BaseServlet {

	private CategoryService categoryService = new CategoryService();
	/**
	 * 查找所有分类
	 */
	public String findAll(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/jsps/left.jsp";
	}
}
