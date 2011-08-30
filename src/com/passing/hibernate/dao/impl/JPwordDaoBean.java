package com.passing.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.passing.hibernate.beans.Jpword;
import com.passing.hibernate.beans.Jpwordmeaning;
import com.passing.hibernate.dao.JPwordDao;

public class JPwordDaoBean extends HibernateDaoSupport implements JPwordDao {
	
	public List<Jpword> getWordListByKana(String kana){
		return getHibernateTemplate().find("from Jpword where KANA=?",kana);
	}
	
	public List<Jpword> getWordListByWord(String word){
		return getHibernateTemplate().find("from Jpword where WORD=?", word);
	}
	
	public List<Jpword> getWordListByKana_right_like(String kana){
		getHibernateTemplate().setMaxResults(5);
		return getHibernateTemplate().find("from Jpword where KANA like ?",kana.substring(0,kana.length()-1)+"_");
	}
	
	public List<Jpword> getWordListByKana_two_right_like(String kana){
		getHibernateTemplate().setMaxResults(5);
		return getHibernateTemplate().find("from Jpword where KANA like ?",kana.substring(0,kana.length()-2)+"__");
	}
	
	public List<Jpword> getWordListByKana_left_like(String kana){
		getHibernateTemplate().setMaxResults(5);
		return getHibernateTemplate().find("from Jpword where KANA like ?","_"+kana.substring(1,kana.length()));
	}

	public List<Jpwordmeaning> getWordListByChineseWord_left_right_like(String chineseWordStr) {
		getHibernateTemplate().setMaxResults(10);
		return getHibernateTemplate().find("from Jpwordmeaning where MEANING like ?","%"+chineseWordStr+"%");
	}
	
	public List<Jpwordmeaning> getWordListByChineseWord_left_like(String chineseWordStr){
		getHibernateTemplate().setMaxResults(10);
		return getHibernateTemplate().find("from Jpwordmeaning where MEANING like ?","%"+chineseWordStr);
	}
	
	public List<Jpwordmeaning> getWordListByChineseWord_right_like(String chineseWordStr){
		getHibernateTemplate().setMaxResults(10);
		return getHibernateTemplate().find("from Jpwordmeaning where MEANING like ?",chineseWordStr+"%");
	}
}
