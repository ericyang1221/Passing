package com.passing.spring.service;

import java.util.List;

import com.passing.hibernate.beans.Jpword;

public interface SearchJpToCn {
	public List<Jpword> doJpToCn(String searchStr);
	public List<Jpword> doJpToCn_like(String searchStr);
}
