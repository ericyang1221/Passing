package com.passing.spring.service;

import java.util.List;

import com.passing.hibernate.beans.Jpwordmeaning;

public interface SearchCnToJp {
	List<Jpwordmeaning> doCnToJp(String chineseWordStr);
}
