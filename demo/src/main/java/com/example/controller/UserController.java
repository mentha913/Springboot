package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.UserBean;
import com.example.service.UserService;

@SpringBootApplication
@RestController
@ComponentScan("com.example.service")
@EntityScan("com.example.bean")
@EnableJpaRepositories("com.example.repository")
public class UserController {
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(UserController.class, args);
	}
	
	@RequestMapping("/user")
	public List<UserBean> getAllUser()  
	{    
		return userService.getAllUsers();    
	}
	
	@RequestMapping("/add")
	public String add()  
	{    
		return "add";    
	}
}
