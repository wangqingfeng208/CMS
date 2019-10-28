package com.wangqingfeng.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wangqingfeng.bean.Channel;
import com.wangqingfeng.mapper.ChannelMapper;

/**
 * @作者 王清锋
 * 2019年10月18日
 * 
 */
@Service
public class ChannelServiceImpl implements ChannelService {
	@Resource
	private ChannelMapper cm;

	@Override
	public List<Channel> selects() {
		// TODO Auto-generated method stub
		return cm.selects();
	}
}
