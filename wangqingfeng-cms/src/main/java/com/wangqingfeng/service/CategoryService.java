package com.wangqingfeng.service;

import java.util.List;

import com.wangqingfeng.bean.Category;

/**
 * @作者 王清锋
 * 2019年10月18日
 * 
 */
public interface CategoryService {

	List<Category> selectsByChannelId(Integer channelId);

}
