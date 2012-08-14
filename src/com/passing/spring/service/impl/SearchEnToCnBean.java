package com.passing.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.passing.hibernate.dao.TbEnWordDao;
import com.passing.spring.service.SearchEnToCn;

public class SearchEnToCnBean implements SearchEnToCn{

	private TbEnWordDao tbEnWordDao;
	
	public List<Object[]> searchEnToCn(String searchStr) {
		
		List<Object[]> tbEnWordInfoWithoutExtdInfo = tbEnWordDao.getEnWordInfoWithoutExtdInfo(searchStr);
		
		// to define a variable to contain right results
		List<Object[]> rstLs = new ArrayList<Object[]>();
		// to filter the search results to make sure that only the right results([word] or [word + space + number]) will returned.
		if (tbEnWordInfoWithoutExtdInfo.size() != 0) {
			for (int i = 0; i < tbEnWordInfoWithoutExtdInfo.size(); i ++) {
				String word = (String)tbEnWordInfoWithoutExtdInfo.get(i)[0];
				// format of the filter is "[word] or [word + space + number]"
				if (word.trim().matches(searchStr+ "( \\d)?")) {
					rstLs.add(tbEnWordInfoWithoutExtdInfo.get(i));
				}
			}
		}
		return rstLs;
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
