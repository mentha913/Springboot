package com.example.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="UDATA", schema = "System")
public class UserBean {
	@javax.persistence.Id
	private String account;
	
	@Column(name="pwd")
	private String pwd;
	
	@Column(name="name")
	private String name;
	
	@Column(name="reg")
	private Date reg;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getReg() {
		return reg;
	}
	public void setReg(Date reg) {
		this.reg = reg;
	}

	@Override
	public String toString() {
		return "account="+account+",pwd="+pwd+",name="+name+",reg="+reg;
	}
	
}
