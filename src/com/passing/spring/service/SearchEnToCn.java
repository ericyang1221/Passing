package com.passing.spring.service;

import java.util.List;

import com.passing.hibernate.beans.TbEnWord;

public interface SearchEnToCn {

	public List<TbEnWord> searchEnToCn(String searchStr);
}
