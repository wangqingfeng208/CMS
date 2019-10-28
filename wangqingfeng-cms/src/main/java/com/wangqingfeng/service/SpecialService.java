package com.wangqingfeng.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wangqingfeng.bean.Special;

/**
 * @作者 王清锋
 * 2019年10月26日
 * 
 */
public interface SpecialService {

	//专题列表
	List<Special> selects();
	//增加专题
	int insert(Special special); 
	//修改专题
	int update(Special special);
	//向专题增加文章
	int insertSpecialArticle(Integer sid ,Integer aid);
	//根据主键查询专题
	Special select(Integer sid);
	//分页查询
	PageInfo<Special> selects(Integer page, Integer pageSize);
}
