package com.passing.hibernate.dao;

import java.util.List;

import com.passing.hibernate.beans.TbEnWord;

public interface TbEnWordDao {
	List<Object[]> getEnWordInfoWithoutExtdInfo(String searchWord);
	List<Object[]> getEnExtdWordInfo(String searchWord);
	
}
