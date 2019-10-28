package com.wangqingfeng.service;

import com.github.pagehelper.PageInfo;
import com.wangqingfeng.bean.User;
import com.wangqingfeng.vo.UserVO;

/**
 * @作者 王清锋
 * 2019年10月15日
 * 
 */
public interface UserService {
	PageInfo<User> selects(String username,Integer page,Integer pageSize);

    
    int insertSelective(UserVO userVO);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);


	User login(User user);
}
