package com.wangqingfeng.mapper;

import java.util.List;

import com.wangqingfeng.bean.Channel;

public interface ChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Channel record);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);

	List<Channel> selects();
}