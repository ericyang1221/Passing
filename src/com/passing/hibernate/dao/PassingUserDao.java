package com.passing.hibernate.dao;

import java.util.List;

import com.passing.hibernate.beans.PassingUser;

public interface PassingUserDao {
	List<PassingUser> getUser(String name,String password);
	Boolean insert(PassingUser user);
	List<PassingUser> getPassingUserByName(String username);
	List<PassingUser> getPassingUserByEmail(String emailAddress);
}
