package com.wangqingfeng.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.wangqingfeng.bean.Article;
import com.wangqingfeng.bean.ArticleWithBLOBs;
import com.wangqingfeng.bean.User;
import com.wangqingfeng.service.ArticleService;
import com.wangqingfeng.service.UserService;
import com.wangqingfeng.utils.ArticleEnum;
import com.wangqingfeng.utils.PageUtil;
import com.wangqingfeng.vo.ArticleVO;

/**
 * @作者 王清锋
 * 2019年10月17日
 * 
 */
@Controller
@RequestMapping("my")
public class MyController {
	
	@Resource
	private ArticleService as;
	
	@Resource
	private UserService us;
	
	@RequestMapping(value = {"","/","index"})
	public String index() {
		return "my/index";
	}
	 /**
	  * 根据登陆用户显示个人文章
	  */
	@RequestMapping("articles")
	public String selectsByUser(Model m, Article article, HttpServletRequest req, @RequestParam(defaultValue = "1")Integer page) {
		Integer pageSize = 10;
		if(article.getStatus() == null) {
			article.setStatus(0);
		}
		HttpSession session = req.getSession(false);
		if(session == null) {
			return "redirect:/passport/login";
		}
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());
		article.setContentType(ArticleEnum.HTML.getCode());
		PageInfo<ArticleWithBLOBs> pi = as.selects(article, page, pageSize);
		String pages = PageUtil.page(page, pi.getPages(), "/my/articles", pageSize);
		m.addAttribute("articles", pi.getList());
		m.addAttribute("article", article);
		m.addAttribute("pages", pages);
		return "my/articles";
	}
	
	
	/**
	 * 	跳转到发布文章页面
	 */
	@GetMapping("/article/publish")
	public String publish() {
		return "my/publish";
	}
	
	
	/**
	 * 	发布新文章
	 */
	@ResponseBody
	@RequestMapping("publish")
	public boolean publish(ArticleWithBLOBs article, MultipartFile file,HttpServletRequest req) {
		if(!file.isEmpty()) {
			String path = "D:/png/";
			String of = file.getOriginalFilename();
			String filename = UUID.randomUUID()+of.substring(of.lastIndexOf("."));
			try {
				file.transferTo(new File(path+filename));
				article.setPicture(filename);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		HttpSession session = req.getSession(false);
		if(session == null) {
			return false;
		}
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());
		article.setStatus(0);
		article.setDeleted(0);
		article.setHits(0);
		article.setHot(0);
		article.setCreated(new Date());
		article.setUpdated(new Date());
		article.setContentType(ArticleEnum.HTML.getCode());//文章类型
		return as.insertSelective(article)>0;
	}
	
	/**
	 * 	查询文章详情
	 */
	@GetMapping("article")
	public String article(Model m, Integer id) {
		ArticleWithBLOBs article = as.selectByPrimaryKey(id);
		m.addAttribute("article", article);
		return "my/article";
	}
	
	/**
	 * 	跳转到修改文章页面
	 */
	@GetMapping("publish")
	public String publish(Model m, Integer id) {
		ArticleWithBLOBs article = as.selectByPrimaryKey(id);
		m.addAttribute("article", article);
		return "my/articleupdate";
	}
	
	/**
	 * 	修改文章
	 */
	@ResponseBody
	@PostMapping("publishUpdate")
	public boolean publishUpdate(ArticleWithBLOBs article, MultipartFile file,HttpServletRequest req) {
		if(!file.isEmpty()) {
			String path = "D:/png/";
			String of = file.getOriginalFilename();
			String filename = UUID.randomUUID()+of.substring(of.lastIndexOf("."));
			try {
				file.transferTo(new File(path+filename));
				article.setPicture(filename);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		article.setUpdated(new Date());
		article.setStatus(0);
		return as.updateByPrimaryKeySelective(article)>0;
	}
	
	/**
	 * 	跳转到发布图片集页面
	 */
	@GetMapping("/articlepic/publish")
	public String publishPic() {
		return "my/publishpic";
	}
	
	/**
	 * 	发布图片集的方法
	 */
	@ResponseBody
	@RequestMapping("publishpic")
	public boolean publishpic(HttpServletRequest req, ArticleWithBLOBs article, MultipartFile[] files, String[] descr) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		if(files.length > 0) {
			int i = 0;
			for (MultipartFile file : files) {
				if(!file.isEmpty()) {
					String path = "D:/png/";
					String of = file.getOriginalFilename();
					String filename = UUID.randomUUID()+of.substring(of.lastIndexOf("."));
					try {
						file.transferTo(new File(path+filename));
						article.setPicture(filename);
						ArticleVO avo = new ArticleVO();
						avo.setDescr(descr[i]);
						avo.setUrl(filename);
						list.add(avo);
						i++;
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		HttpSession session = req.getSession(false);
		if(session == null) {
			return false;
		}
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());
		article.setStatus(0);
		article.setDeleted(0);
		article.setHits(0);
		article.setHot(0);
		article.setCreated(new Date());
		article.setUpdated(new Date());
		article.setContentType(ArticleEnum.IMAGE.getCode());
		Gson gson = new Gson();
		article.setContent(gson.toJson(list));
		return as.insertSelective(article)>0;
	}
	
	/**
	 * 	去修改个人信息的页面
	 */
	@GetMapping("user/update")
	public String update(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session==null) {
			return "redirect:/passport/login";
		}
		User user = (User) session.getAttribute("user");
		User user2 = us.selectByPrimaryKey(user.getId());
		req.setAttribute("user", user2);
		return "my/user";
	}
	
	/**
	 * 	修改用户信息
	 */
	@ResponseBody
	@PostMapping("user/update")
	public boolean update(User user) {
		return us.updateByPrimaryKeySelective(user)>0;
	}
}
