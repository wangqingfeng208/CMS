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
public class AdminInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(false);
		if(session != null) {
			Object admin = session.getAttribute("admin");
			if(admin != null) {
				return true;
			}else {
				req.setAttribute("error", "您不是管理员,请登录管理员账号");
				session.invalidate();
				req.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(req, resp);
				return false;
			}
		}else {
			req.setAttribute("error", "请登陆后再试");
			req.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(req, resp);
			return false;
		}
	}
}
