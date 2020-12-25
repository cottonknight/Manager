package com.bcb.entity;

public class User {
	private int uid;
	private String name;
	private String pwd;
	
	public User() {
		super();
	}
	
	public User(String name, String pwd) {
		super();
		this.name = name;
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}

	public User(int uid, String name, String pwd) {
		super();
		this.uid = uid;
		this.name = name;
		this.pwd = pwd;
	}
	
	
}
