package com.passing.hibernate.dao;

import java.util.List;

import com.passing.hibernate.beans.TbEnWord;

public interface TbEnWordDao {
	List<TbEnWord> getEnWord(String searchWord);
	
}