package com.wangqingfeng.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @作者 王清锋
 * 2019年10月22日
 * 
 */
public class MyInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(false);
		if(session != null) {
			Object user = session.getAttribute("user");
			if(user != null) {
				return true;
			}
		}
		req.setAttribute("error", "请先登录");
		req.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(req, resp);
		return false;
	}
}
