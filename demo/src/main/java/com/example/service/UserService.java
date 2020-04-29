package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.bean.UserBean;
import com.example.repository.UserRepository;
import com.google.gson.Gson;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	public List<UserBean> getAllUsers()  
	{    
		List<UserBean>userBeans = new ArrayList<UserBean>();
		userRepository.findAll().forEach(userBeans::add);
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(userBeans);	
		
		return userBeans;    
	}   
	
	public void addUser(UserBean userBean) {
		userRepository.save(userBean);
		 
	}
	
	
}
