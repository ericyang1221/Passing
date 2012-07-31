package com.passing.spring.service.impl;

import java.util.List;

import com.passing.hibernate.dao.TbEnWordDao;
import com.passing.spring.service.SearchEnToCn;

public class SearchEnToCnBean implements SearchEnToCn{

	private TbEnWordDao tbEnWordDao;
	
	public List<Object[]> searchEnToCn(String searchStr) {
		
		List<Object[]> tbEnWordInfoWithoutExtdInfo = tbEnWordDao.getEnWordInfoWithoutExtdInfo(searchStr);
		
		return tbEnWordInfoWithoutExtdInfo;
	}
	
	public List<Object[]> searchEnToCnExtdInfo(String searchStr) {
		
		List<Object[]> tbEnExtdWordInfo = tbEnWordDao.getEnExtdWordInfo(searchStr);
		
		return tbEnExtdWordInfo;
	}


	public TbEnWordDao getTbEnWordDao() {
		return tbEnWordDao;
	}


	public void setTbEnWordDao(TbEnWordDao tbEnWordDao) {
		this.tbEnWordDao = tbEnWordDao;
	}
}
