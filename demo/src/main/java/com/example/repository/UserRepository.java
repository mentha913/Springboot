package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.bean.UserBean;
@Repository
public interface UserRepository extends CrudRepository<UserBean,String>{
	
}
