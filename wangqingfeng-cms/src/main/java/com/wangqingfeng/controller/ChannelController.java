package com.wangqingfeng.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqingfeng.bean.Category;
import com.wangqingfeng.bean.Channel;
import com.wangqingfeng.service.CategoryService;
import com.wangqingfeng.service.ChannelService;

/**
 * @作者 王清锋
 * 2019年10月22日
 * 
 */
@Controller
@RequestMapping("channel")
public class ChannelController {
	@Resource
	private ChannelService cs;
	@Resource
	private CategoryService cgs;
	
	/**
	 * 	查询有栏目,动态加载下拉菜单
	 */
	@ResponseBody
	@RequestMapping("channels")
	public List<Channel> selects(){
		return cs.selects();
	}
	
	/**
	 * 	根据栏目查询分类,二级联动
	 */
	@ResponseBody
	@RequestMapping("selectCategorysByCid")
	public List<Category> selectCategorysByCid(Integer cid){
		return cgs.selectsByChannelId(cid);
	}
}
