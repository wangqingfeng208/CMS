package com.wangqingfeng.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wangqingfeng.bean.User;
import com.wangqingfeng.exception.CMSException;
import com.wangqingfeng.service.UserService;
import com.wangqingfeng.vo.UserVO;

/**
 * @作者 王清锋
 * 2019年10月17日
 * 
 */
@Controller
@RequestMapping("passport")
public class PassportController {
	@Resource
	private UserService us;
	
	@GetMapping("reg")
	public String reg() {
		return "passport/reg";
	}
	
	@GetMapping("login")
	public String login() {
		return "passport/login";
	}
	
	@PostMapping("reg")
	public String reg(Model m,UserVO userVO,RedirectAttributes attributes) {
		try {
			userVO.setCreated(new Date());
			us.insertSelective(userVO);
			attributes.addFlashAttribute("userVO", userVO);
			return "redirect:login";
		} catch (CMSException cms) {
			// TODO Auto-generated catch block
			cms.printStackTrace();
			m.addAttribute("error", cms.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			m.addAttribute("error", "系统异常");
		}
		return "passport/reg";
	}
	
	@PostMapping("login")
	public String login(Model m, User user, HttpSession session) {
		try {
			User u = us.login(user);
			if(u.getRole().equals("0")) {
				session.setAttribute("user", u);
				return "redirect:/my/index";
			}
			session.setAttribute("admin", u);
			return "redirect:/admin";
		} catch (CMSException cms) {
			// TODO Auto-generated catch block
			cms.printStackTrace();
			m.addAttribute("error", cms.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			m.addAttribute("error", "系统异常");
		}
		return "passport/login";
	}
	
	@GetMapping("logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/passport/login";
	}
}
