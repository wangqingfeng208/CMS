package com.wangqingfeng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqingfeng.bean.Special;

/**
 * @作者 王清锋
 * 2019年10月26日
 * 
 */
public interface SpeciaMapper {

	int insert(Special special);

	int update(Special special);

	int insertSpecialArticle(@Param("sid")Integer sid, @Param("aid")Integer aid);

	List<Special> selects();

	Special select(Integer sid);

}
