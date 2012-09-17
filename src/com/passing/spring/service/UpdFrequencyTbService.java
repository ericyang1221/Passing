package com.passing.spring.service;

import java.util.List;

import com.passing.hibernate.beans.TbEnFrequency;

public interface UpdFrequencyTbService {

	/**
	 * @param word search word
	 * @return 
	 */
	public List<TbEnFrequency> searchWord(String word);
	public boolean insertWord(TbEnFrequency wordInFrequency);
	public boolean updateWordFrequency(TbEnFrequency wordInFrequency);
}
