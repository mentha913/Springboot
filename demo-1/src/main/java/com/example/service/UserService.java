package com.example.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.UserBean;
import com.example.repository.UserRepository;



@Service
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
	
	
	
	
}
