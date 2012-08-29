package com.passing.hibernate.dao;

import java.util.List;

import com.passing.hibernate.beans.TbEnFrequency;

public interface TbEnFrequencyDao {
	/**
	 * @param searchWord
	 * @return a list contains matched records for auto complete source
	 */
	List<TbEnFrequency> searchForAutoComplete(String searchWord);
	
	
}
