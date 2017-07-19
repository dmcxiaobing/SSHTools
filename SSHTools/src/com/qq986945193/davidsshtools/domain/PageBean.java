package com.qq986945193.davidsshtools.domain;

import java.util.List;

import org.junit.Test;

/**
 * 关于页码的Javabean
 * 
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class PageBean<T> {
	private Integer totalPage;// 总页数
	private String currentPage;// 当前页
	private Integer totalSize;// 总记录数
	private Integer pageSize;// 每页记录数
	private List<T> datas;// 当前页记录

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

}
