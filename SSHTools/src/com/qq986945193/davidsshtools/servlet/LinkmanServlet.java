package com.qq986945193.davidsshtools.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.david.webtools.common.base.BaseServlet;
import com.david.webtools.common.utils.CommonUtils;
import com.qq986945193.davidsshtools.domain.Linkman;
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
	 * 查看联系人列表联系人
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 查询联系人列表
		List<Linkman> lists = linkmanService.findAll();
		request.setAttribute("list", lists);
		return "/jsp/linkman/list.jsp";

	}
}
