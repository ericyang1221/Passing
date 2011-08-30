package com.passing.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.passing.hibernate.beans.Jpwordmeaning;
import com.passing.hibernate.dao.JPwordDao;
import com.passing.spring.service.SearchCnToJp;

public class SearchCnToJpBean implements SearchCnToJp {
	private JPwordDao jpwordDao;
	
	public JPwordDao getJpwordDao() {
		return jpwordDao;
	}

	public void setJpwordDao(JPwordDao jpwordDao) {
		this.jpwordDao = jpwordDao;
	}

	public List<Jpwordmeaning> doCnToJp(String chineseWordStr){
		List<Jpwordmeaning> jpwordMeaningList = new ArrayList<Jpwordmeaning>();
		jpwordMeaningList.addAll(getJpwordDao().getWordListByChineseWord_left_like(chineseWordStr));
		jpwordMeaningList.addAll(getJpwordDao().getWordListByChineseWord_left_right_like(chineseWordStr));
		jpwordMeaningList.addAll(getJpwordDao().getWordListByChineseWord_right_like(chineseWordStr));
		return jpwordMeaningList;
	}
}
