package com.passing.spring.service.impl;

import java.util.List;

import com.passing.hibernate.beans.TbEnWord;
import com.passing.hibernate.dao.TbEnWordDao;
import com.passing.spring.service.SearchEnToCn;

public class SearchEnToCnBean implements SearchEnToCn{

	private TbEnWordDao tbEnWordDao;
	
	public List<TbEnWord> searchEnToCn(String searchStr) {
		
		List<TbEnWord> tbEnWord = tbEnWordDao.getEnWord(searchStr);
		
		return tbEnWord;
	}


	public TbEnWordDao getTbEnWordDao() {
		return tbEnWordDao;
	}


	public void setTbEnWordDao(TbEnWordDao tbEnWordDao) {
		this.tbEnWordDao = tbEnWordDao;
	}
}
