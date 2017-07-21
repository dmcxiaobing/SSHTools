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
	private Integer currentPage;// 当前页
	private Integer totalSize;// 总记录数
	private Integer pageSize;// 每页记录数
	private List<T> datas;// 锁查询出来的记录
	private String url;//这是比如筛选的话，里面的条件
	

	/**
	 * 返回总页数
	 */
	public int getTotalPage() {
		//总记录数 除以 每页记录数
		int totalPage = totalSize / pageSize;
		//如果正好能够整除，则总页数为总页数tp,否则为tp+1
		return totalSize % pageSize == 0 ? totalPage : totalPage + 1;
	}

	/**
	 * 返回当前页首行的下标
	 */
	public int getIndex(){
		return (currentPage-1)*currentPage;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPageSize() {
		return pageSize;
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

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

}
