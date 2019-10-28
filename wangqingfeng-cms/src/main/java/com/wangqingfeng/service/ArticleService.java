package com.wangqingfeng.service;

import com.github.pagehelper.PageInfo;
import com.wangqingfeng.bean.Article;
import com.wangqingfeng.bean.ArticleWithBLOBs;

/**
 * @作者 王清锋
 * 2019年10月16日
 * 
 */
public interface ArticleService {

	int deleteByPrimaryKey(Integer id);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);
	
	PageInfo<ArticleWithBLOBs> selects(Article article, Integer page, Integer pageSize);

	int updateByPrimaryKeySelective(ArticleWithBLOBs article);

}
