package com.passing.hibernate.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.passing.hibernate.dao.PassingUserDao;

public class TestPassingUserDao {

	public static void main(String[] args) {
		ApplicationContext ctx=new FileSystemXmlApplicationContext("WebContent/WEB-INF/config/spring/data-access-config.xml");
		PassingUserDao dao=(PassingUserDao)ctx.getBean("passingUserDao");
		System.out.println(dao.getUser("name", "password").size());
	}
}
