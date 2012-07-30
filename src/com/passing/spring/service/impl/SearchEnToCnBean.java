package com.passing.spring.service.impl;

import java.util.List;

import com.passing.hibernate.beans.TbEnWord;
import com.passing.hibernate.dao.TbEnWordDao;
import com.passing.spring.service.SearchEnToCn;

public class SearchEnToCnBean implements SearchEnToCn{

	private TbEnWordDao tbEnWordDao;
	
	public List<Object[]> searchEnToCn(String searchStr) {
		
		List<Object[]> tbEnWordInfoWithoutExtdInfo = tbEnWordDao.getEnWordInfoWithoutExtdInfo(searchStr);
		List<Object[]> tbEnExtdWordInfo = tbEnWordDao.getEnExtdWordInfo(searchStr);
		
		return tbEnWordInfoWithoutExtdInfo;
	}


	public TbEnWordDao getTbEnWordDao() {
		return tbEnWordDao;
	}


	public void setTbEnWordDao(TbEnWordDao tbEnWordDao) {
		this.tbEnWordDao = tbEnWordDao;
	}
}
