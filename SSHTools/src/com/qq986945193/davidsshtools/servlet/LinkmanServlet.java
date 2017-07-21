package com.qq986945193.davidsshtools.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.david.webtools.common.base.BaseServlet;
import com.david.webtools.common.utils.CommonUtils;
import com.qq986945193.davidsshtools.domain.Linkman;
import com.qq986945193.davidsshtools.domain.PageBean;
import com.qq986945193.davidsshtools.service.LinkmanService;

/**
 * 联系人的servlet
 * 
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class LinkmanServlet extends BaseServlet {

	private LinkmanService linkmanService = new LinkmanService();

	/**
	 * 添加联系人
	 */
	public String addLinkman(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到用户输入的联系人属于哪个客户ID
		try {
			String custId = request.getParameter("cust_id");
			Linkman formlinkman = CommonUtils.toBean(request.getParameterMap(), Linkman.class);
			linkmanService.addLinkman(formlinkman, custId);
			return "/jsp/success.jsp";
		} catch (Exception e) {
			return "/jsp/error.jsp";
		}

	}

	/**
	 * 查看联系人列表联系人  这里使用离线查询
	 */
	public String findAllByDetachedCriteria(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		创建离线查询对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Linkman.class);
		String formName = request.getParameter("lkmName");
		int currentPage = getCurrentPage(request);
		//添加查询的条件
		if (formName!=null && !formName.trim().isEmpty()) {
			//拼接查询的条件。
			detachedCriteria.add(Restrictions.like("lkm_name", "%"+formName+"%"));
		}
		// 查询联系人列表
		List<Linkman> lists = linkmanService.findAllByDetachedCriteria(detachedCriteria);
		request.setAttribute("list", lists);
		return "/jsp/linkman/list.jsp";

	}
	

	/**
	 * 查看联系人列表联系人  这里使用离线查询
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String formName = request.getParameter("lkmName");
		//获得当前页
		int currentPage = getCurrentPage(request);
		//获取到每页的记录数
		int pageSize = Integer.parseInt(getPageSize(request.getParameter("pageSize")));
		//新建一个页码的javabean
		PageBean<Linkman> pageBean = new PageBean<Linkman>();
		//设置当前页
		pageBean.setCurrentPage(currentPage);
		//设置每页记录数
		pageBean.setPageSize(10);
		//设置条件
		System.out.println(GetUrl(request));
		pageBean.setUrl(GetUrl(request));
		//获取总记录数
		int totalSize = linkmanService.pageCountQuery(formName);
		System.out.println(totalSize);
		pageBean.setTotalSize(totalSize);
		// 查询联系人列表
		List<Linkman> lists = linkmanService.findAll(formName,currentPage,pageSize);
		pageBean.setDatas(lists);
		request.setAttribute("pageBean", pageBean);
		return "/jsp/linkman/list.jsp";

	}
	/**
	 * 获取到每页显示的记录数//默认每页记录数为10条记录
	 */
	private String getPageSize(String pageSize) {
		if (pageSize == null || !pageSize.trim().isEmpty()) {
			return 10+"";
		}else {
			return pageSize;
		}
	}
	
	
	
	/**
	 * 截取url
	 */
	private String GetUrl(HttpServletRequest request) {
		// 获取请求的参数
		// http://localhost/test.do?a=b&c=d&e=f
		// 通过request.getQueryString()得到的是
		// a=b&c=d&e=f
		/**
		 * 如果url中存在currentPage,则需要把currentPage截取下去，不要它
		 */
		String url = request.getQueryString();
		//System.out.println("url:" + url);
		int index = url.lastIndexOf("&currentPage=");
		// 如果等于-1则说明不存在
		if (index == -1) {
			return url;
		}
		return url.substring(0, index);
	}

	/**
	 * 获得当前的页码 如果为null，默认为第一页，
	 */
	private int getCurrentPage(HttpServletRequest request) {
		String currentPage = request.getParameter("currentPage");
		if (currentPage != null && !currentPage.trim().isEmpty()) {
			return Integer.parseInt(currentPage);
		} else {
			return 1;
		}
	}
	
}
