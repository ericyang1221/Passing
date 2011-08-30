package com.passing.hibernate.test;


import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.passing.hibernate.beans.Jpword;
import com.passing.hibernate.beans.Jpwordmeaning;
import com.passing.hibernate.dao.JPwordDao;

public class JpwordDaoTester {
	JPwordDao dao;

	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx=new FileSystemXmlApplicationContext("WebContent/WEB-INF/config/spring/data-access-config.xml");
		dao=(JPwordDao)ctx.getBean("jpwordDao");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetWorldListByChineseWord(){
		//TODO
//		List<Jpwordmeaning> list = dao.getWorldListByChineseWord("明天");
//		System.out.println(list.get(0).getJpword().getKana());
	}
	
//	@Test
	public void testGetWordListByKana(){
		List<Jpword> list = dao.getWordListByKana("あ");
		Jpword word = (Jpword)list.get(0);
		System.out.println(word.getKana());
		System.out.println(word.getWord());
		System.out.println(word.getId());
		System.out.println(((Jpwordmeaning)word.getJpwordmeanings().toArray()[0]).getMeaning());
	}
}
