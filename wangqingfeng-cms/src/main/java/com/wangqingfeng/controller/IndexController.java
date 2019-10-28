package com.wangqingfeng.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.wangqingfeng.bean.Article;
import com.wangqingfeng.bean.ArticleWithBLOBs;
import com.wangqingfeng.bean.Category;
import com.wangqingfeng.bean.Channel;
import com.wangqingfeng.bean.Special;
import com.wangqingfeng.service.ArticleService;
import com.wangqingfeng.service.CategoryService;
import com.wangqingfeng.service.ChannelService;
import com.wangqingfeng.service.SpecialService;
import com.wangqingfeng.utils.ArticleEnum;
import com.wangqingfeng.utils.DateUtil;
import com.wangqingfeng.utils.PageUtil;
import com.wangqingfeng.vo.ArticleVO;

/**
 * @作者 王清锋
 * 2019年10月18日
 * 
 */
@Controller
public class IndexController {
	@Resource
	private ArticleService as;
	
	@Resource
	private ChannelService cs;
	
	@Resource
	private CategoryService cgs;
	
	@Resource
	private SpecialService ss;
	
	@RequestMapping(value = {"","/","index"})
	public String index(Model m, Article article, @RequestParam(defaultValue = "1")Integer page) {
		Integer pageSize = 4;
		long start = System.currentTimeMillis();
		System.out.println("访问开始时间:==================>" + start);
		article.setStatus(1);//审核通过
		article.setDeleted(0);//未删除
		Thread th1 = null;
		Thread th2 = null;
		Thread th3 = null;
		Thread th4 = null;
		Thread th5 = null;
		Thread th6 = null;
		th1 = new Thread(new Runnable() {
			@Override
			public void run() {
				//显示左侧导航栏
				List<Channel> channels = cs.selects();
				m.addAttribute("channels", channels);
			}
		});
		//显示分类内容
		th2 = new Thread(new Runnable() {
			@Override
			public void run() {
				if(article.getChannelId() == null) {
					//如果栏目为空则查询则查询热点文章
					article.setHot(1);
					PageInfo<ArticleWithBLOBs> pi = as.selects(article, page, pageSize);
					String pages = PageUtil.page(page, pi.getPages(), "/", pageSize);
					m.addAttribute("hotArticles", pi.getList());
					m.addAttribute("pages", pages);
				}else {
					//查询栏目下的分类
					List<Category> categories = cgs.selectsByChannelId(article.getChannelId());
					m.addAttribute("categories", categories);
					PageInfo<ArticleWithBLOBs> pi = as.selects(article, page, pageSize);
					String url = "/?ChannelId="+article.getChannelId();
					if(article.getCategoryId()!=null) {
						url += "&categoryId="+article.getCategoryId();
					}
					String pages = PageUtil.page(page, pi.getPages(), url, pageSize);
					m.addAttribute("articles", pi.getList());
					m.addAttribute("pages", pages);
				}
				m.addAttribute("article", article);
			}
		});
		th3 = new Thread(new Runnable() {
			@Override
			public void run() {
				//最新文章
				Article articlehot = new Article();
				articlehot.setStatus(1);
				articlehot.setContentType(ArticleEnum.HTML.getCode());
				PageInfo<ArticleWithBLOBs> pi2 = as.selects(articlehot, 1, 10);
				m.addAttribute("articlehot", pi2.getList());
				
			}
		});
		th4 = new Thread(new Runnable() {
			@Override
			public void run() {
				//右侧栏目24小时热点文章
				Article article24 = new Article();
				article24.setHot(1);
				article24.setCreated(DateUtil.getYesterday(new Date()));
				article24.setContentType(ArticleEnum.HTML.getCode());
				PageInfo<ArticleWithBLOBs> pi = as.selects(article24, 1, 100);
				m.addAttribute("article24", pi.getList());
				
			}
		});
		th5 = new Thread(new Runnable() {
			@Override
			public void run() {
				//图片集
				Article articlepic = new Article();
				articlepic.setStatus(1);
				articlepic.setContentType(ArticleEnum.IMAGE.getCode());
				PageInfo<ArticleWithBLOBs> pi3 = as.selects(articlepic, 1, 10);
				List<ArticleWithBLOBs> list = pi3.getList();
				System.out.println(list.size());
				m.addAttribute("articlepics", pi3.getList());
				
			}
		});
		
		th6 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				List<Special> list = ss.selects();
				
				m.addAttribute("specialList", list);
			}
		});
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		th5.start();
		th6.start();
		try {
			th1.join();
			th2.join();
			th3.join();
			th4.join();
			th5.join();
			th6.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("访问结束时间:==================>" + end);
		System.out.println("访问用时:==================>" + (end - start));
		return "index/index";
	}
	
	@GetMapping("select")
	public String select(Model m,Integer id) {
		ArticleWithBLOBs article = as.selectByPrimaryKey(id);
		m.addAttribute("article", article);
		return "index/article";
	}
	
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
		return "index/articlepic";
	}
	
}
