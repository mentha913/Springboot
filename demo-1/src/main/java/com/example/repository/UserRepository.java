package com.example.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.bean.UserBean;
@Repository
public interface UserRepository extends CrudRepository<UserBean,String>{
	
	@Query(value="SELECT * FROM UDATA WHERE ACCOUNT=?1",nativeQuery=true)
	UserBean queryByAccount(String account);
	
	@Query(value="INSERT INTO UDATA(ACCOUNT,PWD,NAME,REG)VALUES(?1,?2,?3,SYSDATE)",nativeQuery=true)
	@Modifying
	@Transactional
	void addUData(String account, String pwd, String name);
	
	@Query(value="UPDATE UDATA SET PWD=?1,NAME=?2 WHERE ACCOUNT=?3",nativeQuery=true)
	@Modifying
	@Transactional
	void modifyByAccount(String pwd,String name,String account);
	
	@Query(value="DELETE FROM UDATA WHERE ACCOUNT=?1 ",nativeQuery=true)
	@Modifying
	@Transactional
	void deleteByAccount(String account);

}
