package com.wangqingfeng.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.wangqingfeng.bean.Article;
import com.wangqingfeng.bean.ArticleWithBLOBs;
import com.wangqingfeng.bean.Special;
import com.wangqingfeng.bean.User;
import com.wangqingfeng.service.ArticleService;
import com.wangqingfeng.service.SpecialService;
import com.wangqingfeng.service.UserService;
import com.wangqingfeng.utils.PageUtil;
import com.wangqingfeng.vo.ArticleVO;



/**
 * @作者 王清锋
 * 2019年10月15日
 * 
 */
@RequestMapping("admin")
@Controller
public class UserController {
	@Resource
	private UserService us;
	
	@Resource 
	private ArticleService as;
	
	@Resource
	private SpecialService ss;
	//进入主页的方法
	@RequestMapping(value = {"index","/",""})
	public String index() {
		return "admin/index";
	}
	/**
	 * 	显示用户列表和调用分页方法
	 */
	@GetMapping("user")
	public String users(Model model,@RequestParam(defaultValue = "")String username ,@RequestParam(defaultValue = "1")Integer page) {
		Integer pageSize = 3;
		PageInfo<User> pi = us.selects(username, page, pageSize);
		String pages = PageUtil.page(page, pi.getPages(), "/admin/user?username="+username, pageSize);
		model.addAttribute("user", pi.getList());
		model.addAttribute("username", username);
		model.addAttribute("pages", pages);
		return "admin/user";
	}
	/**
	 * 	修改用户状态
	 */
	@ResponseBody
	@PostMapping("updateUser")
	public boolean updateUser(User user) {
		return us.updateByPrimaryKeySelective(user)>0;
	}
	/**
	 * 	展示文章列表和调用分页方法
	 */
	@GetMapping("articles")
	public String articles(Model m, Article article, @RequestParam(defaultValue = "1")Integer page) {
		Integer pageSize = 3;
		//设置默认审核状态为待审核“0”
		if(article.getStatus() == null) {
			article.setStatus(0);
		}
		String url = "/admin/articles?x=x";
		if(article.getTitle() != null) {
			url += "&title="+article.getTitle();
		}
		if(article.getStatus() != null) {
			url += "&status="+article.getStatus();
		}
		PageInfo<ArticleWithBLOBs> pi = as.selects(article,page,pageSize);
		String pages = PageUtil.page(page, pi.getPages(), url, pageSize);
		m.addAttribute("articles", pi.getList());
		m.addAttribute("article", article);
		m.addAttribute("pages", pages);
		return "admin/articles";
	}
	
	/**
	 * 	显示单个文章详情
	 */
	@GetMapping("article")
	public String article(Model m,Integer id) {
		ArticleWithBLOBs article = as.selectByPrimaryKey(id);
		m.addAttribute("article", article);
		return "admin/article";
	}
	
	/**
	 * 	审核文章
	 */
	@ResponseBody
	@RequestMapping("updateArticle")
	public boolean updateArticle(ArticleWithBLOBs article) {
		int n = as.updateByPrimaryKeySelective(article);
		return n>0;
	}
	
	/**
	 * 	显示单个图片集图片
	 */
	@GetMapping("selectpic")
	public String selectpic(Model m,Integer id) {
		ArticleWithBLOBs article = as.selectByPrimaryKey(id);
		String str = article.getContent();
		JsonArray json = new JsonParser().parse(str).getAsJsonArray();
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		Gson gson = new Gson();
		for (JsonElement jsonElement : json) {
			ArticleVO avo = gson.fromJson(jsonElement, ArticleVO.class);
			list.add(avo);
		}
		m.addAttribute("title", article.getTitle());
		m.addAttribute("list", list);
		return "admin/articlepic";
	}
	
	/**
	 * 	专题文章
	 */
	@GetMapping("special/selects")
	public String specials(Model m,@RequestParam(defaultValue = "1")Integer page) {
		Integer pageSize = 3;
		PageInfo<Special> pi = ss.selects(page,pageSize);
		System.err.println(pi.getPages());
		String pages = PageUtil.page(page, pi.getPages(), "/admin/special/selects", pageSize);
		m.addAttribute("specials", pi.getList());
		m.addAttribute("pages", pages);
		return "admin/specials";
	}
	
	/**
	 * 	跳转到增加专题页面
	 */
	@GetMapping("special/add")
	public String add() {
		return "admin/specialsadd";
	}
	
	/**
	 * 	增加专题
	 */
	@ResponseBody
	@PostMapping("/special/add")
	public boolean add(Special special) {
		return ss.insert(special)>0;
	}
	
	/**
	 * 	查看单个专题
	 */
	@GetMapping("special/select")
	public String select(Model m ,Integer sid) {
		Special special = ss.select(sid);
		m.addAttribute("s", special);
		return "admin/special";
	}
	
	/**
	 * 	为专题增加文章
	 */
	@ResponseBody
	@PostMapping("/special/addArticle")
	public boolean addArticle(Integer sid,Integer aid) {
		return ss.insertSpecialArticle(sid, aid)>0;
	}
}
