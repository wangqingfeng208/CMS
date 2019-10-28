package com.wangqingfeng.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wangqingfeng.bean.Category;
import com.wangqingfeng.mapper.CategoryMapper;

/**
 * @作者 王清锋
 * 2019年10月18日
 * 
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Resource
	private CategoryMapper cm;

	@Override
	public List<Category> selectsByChannelId(Integer channelId) {
		// TODO Auto-generated method stub
		return cm.selectByChannelId(channelId);
	}
}
