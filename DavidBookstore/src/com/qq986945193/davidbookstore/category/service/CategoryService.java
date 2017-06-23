package com.qq986945193.davidbookstore.category.service;

import java.util.List;

import com.qq986945193.davidbookstore.category.dao.CategoryDao;
import com.qq986945193.davidbookstore.category.domain.Category;
/**
 * 分类的持久层
 */
public class CategoryService {

	private CategoryDao categoryDao = new CategoryDao();
	/**
	 * 查询所有分类
	 * @return
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

}
