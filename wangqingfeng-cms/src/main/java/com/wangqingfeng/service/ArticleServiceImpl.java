package com.wangqingfeng.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangqingfeng.bean.Article;
import com.wangqingfeng.bean.ArticleWithBLOBs;
import com.wangqingfeng.mapper.ArticleMapper;

/**
 * @作者 王清锋
 * 2019年10月16日
 * 
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	@Resource
	private ArticleMapper am;

	@Override
	public PageInfo<ArticleWithBLOBs> selects(Article article, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, pageSize);
		List<ArticleWithBLOBs> list = am.selects(article);
		return new PageInfo<ArticleWithBLOBs>(list);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return am.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ArticleWithBLOBs record) {
		// TODO Auto-generated method stub
		System.out.println(record);
		return am.insertSelective(record);
	}

	@Override
	public ArticleWithBLOBs selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return am.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record) {
		// TODO Auto-generated method stub
		return am.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ArticleWithBLOBs article) {
		// TODO Auto-generated method stub
		return am.updateByPrimaryKeySelective(article);
	}
}
