package com.wangqingfeng.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.wangqingfeng.bean.User;
import com.wangqingfeng.exception.CMSException;
import com.wangqingfeng.mapper.UserMapper;
import com.wangqingfeng.utils.Md5Util;
import com.wangqingfeng.utils.StringUtil;
import com.wangqingfeng.vo.UserVO;

/**
 * @作者 王清锋
 * 2019年10月15日
 * 
 */
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper um;

	@Override
	public PageInfo<User> selects(String username, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		PageMethod.startPage(page, pageSize);
		List<User> list = um.selects(username);
		return new PageInfo<User>(list);
	}

	@Override
	public int insertSelective(UserVO userVO) {
		// TODO Auto-generated method stub
		if(userVO == null) {
			throw new CMSException("用户名和密码必须输入");
		}
		if(userVO.getUsername() == null) {
			throw new CMSException("用户名不能为空");
		}
		if(userVO.getUsername().length() < 2 || userVO.getUsername().length() > 5) {
			throw  new CMSException("用户名长度为2-5位");
		}
		if(userVO.getPassword() == null) {
			throw new CMSException("密码不能为空");
		}
		if(userVO.getPassword().length() < 6 || userVO.getPassword().length() > 10) {
			throw new CMSException("密码长度为6-10位");
		}
		if(!(userVO.getPassword().equals(userVO.getRepassword()))) {
			throw new CMSException("两次密码输入不一致");
		}
		userVO.setPassword(Md5Util.md5Encoding(userVO.getPassword()));
		return um.insertSelective(userVO);
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return um.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return um.updateByPrimaryKeySelective(record);
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		if(user == null) {
			throw new CMSException("用户名和密码不能为空");
		}
		if(!StringUtil.hasText(user.getUsername())) {
			throw new CMSException("用户名不能为空");
		}
		if(!StringUtil.hasText(user.getPassword())) {
			throw new CMSException("密码不能为空");
		}
		User u = um.login(user.getUsername());
		if(u == null) {
			throw new CMSException("当前用户不存在");
		}
		//if(u.getPassword().equals(Md5Util.md5Encoding(user.getPassword()))) {少了一个非数据库是明码这里是暗码
		if(!u.getPassword().equals(Md5Util.md5Encoding(user.getPassword()))) {
			throw new CMSException("密码不正确");
		}else {
			System.out.println(u.getPassword()+"====="+user.getPassword());
		}
		return u;
	}
}
