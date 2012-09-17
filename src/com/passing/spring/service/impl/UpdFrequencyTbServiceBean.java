package com.passing.spring.service.impl;

import java.util.List;

import com.passing.hibernate.beans.TbEnFrequency;
import com.passing.hibernate.dao.TbEnFrequencyDao;
import com.passing.spring.service.UpdFrequencyTbService;

public class UpdFrequencyTbServiceBean implements UpdFrequencyTbService {

	public UpdFrequencyTbServiceBean() {
		super();
	}
	
	private TbEnFrequencyDao tbEnFrequencyDao;

	@Override
	public List<TbEnFrequency> searchWord(String word) {
		List<TbEnFrequency> wordInFrequencyTb = tbEnFrequencyDao.searchForAutoComplete(word);
		
		return wordInFrequencyTb;
	}

	public TbEnFrequencyDao getTbEnFrequencyDao() {
		return tbEnFrequencyDao;
	}

	public void setTbEnFrequencyDao(TbEnFrequencyDao tbEnFrequencyDao) {
		this.tbEnFrequencyDao = tbEnFrequencyDao;
	}

	@Override
	public boolean insertWord(TbEnFrequency wordInFrequency) {
		boolean rst = tbEnFrequencyDao.insertWord(wordInFrequency);
		return rst;
	}

	@Override
	public boolean updateWordFrequency(TbEnFrequency wordInFrequency) {
		return tbEnFrequencyDao.updateWordFrequency(wordInFrequency);
	}
}
