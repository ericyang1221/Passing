package com.passing.hibernate.dao;

import java.util.List;

import com.passing.hibernate.beans.Jpword;
import com.passing.hibernate.beans.Jpwordmeaning;

public interface JPwordDao {
	public List<Jpword> getWordListByKana(String kana);
	public List<Jpword> getWordListByWord(String word);
	public List<Jpword> getWordListByKana_right_like(String kana);
	public List<Jpword> getWordListByKana_two_right_like(String kana);
	public List<Jpword> getWordListByKana_left_like(String kana);
	public List<Jpwordmeaning> getWordListByChineseWord_left_right_like(String chineseWordStr);
	public List<Jpwordmeaning> getWordListByChineseWord_left_like(String chineseWordStr);
	public List<Jpwordmeaning> getWordListByChineseWord_right_like(String chineseWordStr);
}
