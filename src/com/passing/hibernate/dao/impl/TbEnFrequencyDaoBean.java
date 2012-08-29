package com.passing.hibernate.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.passing.hibernate.beans.TbEnFrequency;
import com.passing.hibernate.dao.TbEnFrequencyDao;

public class TbEnFrequencyDaoBean extends HibernateDaoSupport implements TbEnFrequencyDao {

	@Override
	public List<TbEnFrequency> searchForAutoComplete(String searchWord) {

		String sql =  "from TbEnFrequency where word like '" + searchWord + "%' order by frequency";
		
		@SuppressWarnings("unchecked")
		List<TbEnFrequency> list = getHibernateTemplate().find(sql);

		return list;
	}
	
}