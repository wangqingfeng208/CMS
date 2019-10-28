package com.wangqingfeng.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangqingfeng.bean.Article;
import com.wangqingfeng.bean.ArticleWithBLOBs;
import com.wangqingfeng.bean.Term;
import com.wangqingfeng.exception.CMSException;
import com.wangqingfeng.mapper.ArticleMapper;
import com.wangqingfeng.mapper.TermMapper;
import com.wangqingfeng.utils.StringUtil;

/**
 * @作者 王清锋
 * 2019年10月16日
 * 
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	@Resource
	private ArticleMapper am;
	
	@Resource TermMapper tm;

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
		try {
			//1.发布文章.返回文章的自增主键值
			am.insertSelective(record);
			//2. 判断有没有标签.如果有则进行标签处理
			//获取文章标签
			String terms = record.getTerms();
			if(StringUtil.hasText(terms)) {
				String[] split = terms.split(",");
				for (String str : split) {
					//判断该标签在数据库是否存在
					Term term2 = tm.selectByName(StringUtil.toUniqueTerm(str));
					if(null==term2) {//如果不存在,则插入该标签,并且返回该标签的自增主键值
					    term2 = new Term();
					    term2.setUniqueName(str);
						tm.insert(term2);//插入标签
					}
					//向中间表
					tm.insertArticleTerm(record.getId(), term2.getId());
				}
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException("发布失败");
		}
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
