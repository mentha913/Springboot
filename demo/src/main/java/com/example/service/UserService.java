package com.example.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.bean.UserBean;
import com.example.repository.UserRepository;



@Service
@CacheConfig(cacheNames = "userService")
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	public UserBean findUser(String account)  
	{    
		UserBean userBean = userRepository.queryByAccount(account);
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(userBean);	
		return userBean;    
	}   
	
	public List<UserBean> addUser(String account, String pwd, String name) {
		userRepository.addUData(account, pwd, name);
		List<UserBean> userBeans = new ArrayList<UserBean>();
		userRepository.findAll().forEach(userBeans::add);
		return userBeans;
	}
	
	public UserBean modifyUser(String account, String pwd, String name) {
		userRepository.modifyByAccount(pwd,name,account);
		UserBean userBean = userRepository.queryByAccount(account);
		return userBean;
	}
	
	public List<UserBean> deleteUser(String account) {
		userRepository.deleteByAccount(account);
		List<UserBean> userBeans = new ArrayList<UserBean>();
		userRepository.findAll().forEach(userBeans::add);
		return userBeans;
	}
	
	@Cacheable(value = "userCache")
	public UserBean loginUser(String account, String pwd){
		UserBean userBean = userRepository.verifyIdPwd(account,pwd);
		if(userBean!=null) {
			userRepository.modifyLoginTime(account);
		}
		userBean = findUser(account);
		return userBean;
	}

	@CacheEvict(value = "userCache")
	public UserBean logoutUser(String account, String pwd) {
		if(account!=null && pwd!=null) {
			userRepository.modifyLogoutTime(account);
		}
		UserBean userBean = findUser(account);
		return userBean;
	}
	
}
