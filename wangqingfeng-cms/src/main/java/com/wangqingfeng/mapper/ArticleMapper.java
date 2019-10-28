package com.wangqingfeng.mapper;

import java.util.List;


import com.wangqingfeng.bean.Article;
import com.wangqingfeng.bean.ArticleWithBLOBs;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);

	List<ArticleWithBLOBs> selects(Article article);
}