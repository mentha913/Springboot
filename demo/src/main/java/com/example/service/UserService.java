package com.example.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.bean.UserBean;

import com.example.repository.UserRepository;



@Service
@CacheConfig(cacheNames = "userService")
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RedisTemplate redisTemplate;

	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
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
		
//		boolean haskey = redisTemplate.hasKey(account);
//		System.out.println("haskey="+haskey);
		
		UserBean userBean = userRepository.verifyIdPwd(account,pwd);
		if(userBean!=null) {
			userRepository.modifyLoginTime(account);
		}
		userBean = findUser(account);
//		System.out.println("haskey2="+haskey);
		return userBean;
	}

	@CacheEvict(value = "userCache")
	public UserBean logoutUser(String account, String pwd) {
//		boolean haskey = redisTemplate.hasKey("userCache");
//		System.out.println("haskey3="+haskey);
		
		if(account!=null && pwd!=null) {
			userRepository.modifyLogoutTime(account);
		}
		UserBean userBean = findUser(account);
//		System.out.println("haskey4="+haskey);
		return userBean;
	}
	
	@Scheduled(cron="0/3 * * * * ?")
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
	}
	
}
