package com.passing.hibernate.test;


import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.passing.hibernate.beans.TbEnWord;
import com.passing.hibernate.dao.TbEnWordDao;

public class TbEnWordDaoTester {
	TbEnWordDao dao;

	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx=new FileSystemXmlApplicationContext("WebContent/WEB-INF/config/spring/data-access-config.xml");
		dao=(TbEnWordDao)ctx.getBean("tbEnWordDao");
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testGetEnWord(){
		List<TbEnWord> list = dao.getEnWordInfoWithExtdInfo("a");
		TbEnWord word = (TbEnWord)list.get(0);
		System.out.println(word.getDict_id());
	}
}
