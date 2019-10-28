package com.wangqingfeng.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangqingfeng.bean.Special;
import com.wangqingfeng.mapper.SpeciaMapper;

/**
 * @作者 王清锋
 * 2019年10月26日
 * 
 */
@Service
public class SpecialServiceImpl implements SpecialService {

	@Resource
	private SpeciaMapper sm;
	@Override
	public List<Special> selects() {
		// TODO Auto-generated method stub
		return sm.selects();
	}

	@Override
	public int insert(Special special) {
		// TODO Auto-generated method stub
		return sm.insert(special);
	}

	@Override
	public int update(Special special) {
		// TODO Auto-generated method stub
		return sm.update(special);
	}

	@Override
	public int insertSpecialArticle(Integer sid, Integer aid) {
		// TODO Auto-generated method stub
		return sm.insertSpecialArticle(sid, aid);
	}

	@Override
	public Special select(Integer sid) {
		// TODO Auto-generated method stub
		return sm.select(sid);
	}

	@Override
	public PageInfo<Special> selects(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, pageSize);
		List<Special> list = sm.selects();
		return new PageInfo<Special>(list);
	}

}
