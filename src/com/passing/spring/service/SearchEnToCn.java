package com.passing.spring.service;

import java.util.List;

import com.passing.hibernate.beans.TbEnWord;

public interface SearchEnToCn {

	public List<Object[]> searchEnToCn(String searchStr);
	public List<Object[]> searchEnToCnExtdInfo(String searchStr);
}
