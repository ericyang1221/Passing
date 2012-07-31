package com.passing.hibernate.dao;

import java.util.List;

import com.passing.hibernate.beans.TbEnWord;

public interface TbEnWordDao {
	/**
	 * @param searchWord
	 * @return a list contains word information but no extend information about this word
	 */
	List<Object[]> getEnWordInfoWithoutExtdInfo(String searchWord);
	
	/**
	 * @param searchWord
	 * @return a list contains extend information about this word
	 */
	List<Object[]> getEnExtdWordInfo(String searchWord);
	
}
