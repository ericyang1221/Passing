package com.passing.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.passing.hibernate.beans.TbEnFrequency;
import com.passing.hibernate.dao.TbEnFrequencyDao;
import com.passing.spring.service.AutoCompleteService;

public class AutoCompleteServiceBean implements AutoCompleteService {

	public AutoCompleteServiceBean() {
		super();
	}
	
	private TbEnFrequencyDao tbEnFrequencyDao;

	@Override
	public List<String> searchForAutoComplete(String searchStr) {
		List<TbEnFrequency> list = tbEnFrequencyDao.searchForAutoComplete(searchStr);
		// Initialize list for matched words
		List<String> wordList = new ArrayList<String>();
		// The record number returned to front-end is 10
		int size = list.size() > 10 ? 10 : list.size();
		for (int i = 0; i < size; i ++) {
			wordList.add(list.get(i).getWord());
		}
		
		return wordList;
	}

	public TbEnFrequencyDao getTbEnFrequencyDao() {
		return tbEnFrequencyDao;
	}

	public void setTbEnFrequencyDao(TbEnFrequencyDao tbEnFrequencyDao) {
		this.tbEnFrequencyDao = tbEnFrequencyDao;
	}
}
