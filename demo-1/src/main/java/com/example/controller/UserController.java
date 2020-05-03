package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bean.UserBean;
import com.example.service.UserService;

@SpringBootApplication
@Controller
@ComponentScan("com.example.service")
@EntityScan("com.example.bean")
@EnableJpaRepositories("com.example.repository")
public class UserController {
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(UserController.class, args);
	}
	
	@ResponseBody
	@GetMapping("/user")
	public UserBean findAccount(@RequestParam String account)  
	{    
		return userService.findUser(account);    
	}
	
	@ResponseBody
	@GetMapping("/add")
	public List<UserBean> addUData(@RequestParam String account, @RequestParam String pwd, @RequestParam String name)  
	{    
		return userService.addUser(account, pwd, name);    
	}

	@ResponseBody
	@GetMapping("/modify")
	public UserBean modifyUData(@RequestParam String account,@RequestParam String pwd, @RequestParam String name)  
	{    
		return userService.modifyUser(account,pwd, name);    
	}

	@ResponseBody
	@GetMapping("/delete")
	public List<UserBean> deleteUData(@RequestParam String account)  
	{    
		System.out.println("controller="+account);
		return userService.deleteUser(account);    
	}	
}
