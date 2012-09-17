package com.passing.hibernate.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.passing.hibernate.beans.TbEnFrequency;
import com.passing.hibernate.dao.TbEnFrequencyDao;
import com.passing.util.LogUtil;

public class TbEnFrequencyDaoBean extends HibernateDaoSupport implements TbEnFrequencyDao {

	@Override
	public List<TbEnFrequency> searchForAutoComplete(String searchWord) {

		String sql =  "from TbEnFrequency where word like '" + searchWord + "%' order by frequency desc, word asc";
		
		@SuppressWarnings("unchecked")
		List<TbEnFrequency> list = getHibernateTemplate().find(sql);

		return list;
	}

	@Override
	public boolean insertWord(TbEnFrequency wordInFrequency) {
//		String sql = "insert into TbEnFrequency";
		try {
			getHibernateTemplate().save(wordInFrequency);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			
			LogUtil.log.debug(e);
			for(StackTraceElement str : e.getStackTrace()) {
				LogUtil.log.debug(str);
			}
		}
		
		return false;
	}

	@Override
	public boolean updateWordFrequency(TbEnFrequency wordInFrequency) {
		try {
			getHibernateTemplate().update(wordInFrequency);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			
			LogUtil.log.debug(e);
			for(StackTraceElement str : e.getStackTrace()) {
				LogUtil.log.debug(str);
			}
			
		}
		return false;
	}
	
}