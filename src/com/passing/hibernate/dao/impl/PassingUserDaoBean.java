package com.passing.hibernate.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.passing.hibernate.beans.PassingUser;
import com.passing.hibernate.dao.PassingUserDao;

public class PassingUserDaoBean extends HibernateDaoSupport implements PassingUserDao {

	public List<PassingUser> getUser(String name, String password) 
	{
		String[] args = {name,password};
		return getHibernateTemplate().find("from PassingUser where USERNAME=? and PASSWORD=?",args);
	}

	public Boolean insert(PassingUser user)
	{
		boolean isSuccess = false;
		try{
			getHibernateTemplate().save(user);
			isSuccess = true;
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return isSuccess;
	}
	public List<PassingUser> getPassingUserByName(String username)
	{
		String qs = "from PassingUser where USERNAME = '" + username + "'";
		return getHibernateTemplate().find(qs);
		
	}
	public List<PassingUser> getPassingUserByEmail(String emailAddress)
	{
		String qs = "from PassingUser where EMAILADDRESS = '"+ emailAddress + "'";
		return getHibernateTemplate().find(qs);
	}
}