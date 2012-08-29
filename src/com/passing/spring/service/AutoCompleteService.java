package com.passing.spring.service;

import java.util.List;

import com.passing.hibernate.beans.TbEnFrequency;

public interface AutoCompleteService {

	/**
	 * @param searchStr search string
	 * @return 
	 */
	public List<String> searchForAutoComplete(String searchStr);
}
