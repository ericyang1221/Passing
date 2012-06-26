package com.passing.hibernate.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.passing.hibernate.beans.TbEnWord;
import com.passing.hibernate.dao.TbEnWordDao;

public class TbEnWordDaoBean extends HibernateDaoSupport implements TbEnWordDao {

//	public static Logger log = Logger.getLogger(TbEnWordDaoBean.class);
	
	public List<TbEnWord> getEnWord(String searchWord) {
		
		String sql = "from TbEnWord where WORD= '" + searchWord + "'";
//		log.info("SQL = " + sql);
		List<TbEnWord> list = getHibernateTemplate().find(sql);
		
//		Session session = getHibernateTemplate().getSessionFactory().openSession();
//		Query q= session.createQuery("from TbEnWord where WORD = ?");
//		q.setParameter(0, searchWord);
//		List<TbEnWord> list = q.list();

		
		return list;
	}

}