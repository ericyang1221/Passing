package com.passing.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.passing.spring.service.SearchJpToCn;
import com.passing.hibernate.beans.Jpword;
import com.passing.hibernate.dao.JPwordDao;

public class SearchJpToCnBean implements SearchJpToCn {
	private JPwordDao jpwordDao;

	public JPwordDao getJpwordDao() {
		return jpwordDao;
	}

	public void setJpwordDao(JPwordDao jpwordDao) {
		this.jpwordDao = jpwordDao;
	}
	
	public List<Jpword> doJpToCn(String searchStr){
		List<Jpword> resultList = getJpwordDao().getWordListByWord(searchStr);
		if(resultList.size() < 1){
			return getJpwordDao().getWordListByKana(searchStr);
		}else{
			return resultList;
		}
	}
	
	public List<Jpword> doJpToCn_like(String searchStr){
		List<Jpword> wordList = new ArrayList<Jpword>();
		if(searchStr.length() > 1){
			List<Jpword> right1List = getJpwordDao().getWordListByKana_right_like(searchStr);
			List<Jpword> right2List = getJpwordDao().getWordListByKana_two_right_like(searchStr);
			List<Jpword> left1List = getJpwordDao().getWordListByKana_left_like(searchStr);
			wordList = right1List;
			wordList.addAll(right2List);
			wordList.addAll(left1List);
		}
		return wordList;		
	}
}