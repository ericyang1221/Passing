package com.passing.spring.service;

public interface RegisterService {

	public String register(String userInfo);
	public boolean isUserExist(String username);
}
