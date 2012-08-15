package com.passing.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.passing.consts.CommonConsts;
import com.passing.consts.SearchEnToCnConsts;
import com.passing.hibernate.dao.TbEnWordDao;
import com.passing.spring.service.SearchEnToCn;

public class SearchEnToCnBean implements SearchEnToCn{

	private TbEnWordDao tbEnWordDao;
	
	public List<Object[]> searchEnToCn(String searchStr) {
		
		List<Object[]> tbEnWordInfoWithoutExtdInfo = tbEnWordDao.getEnWordInfoWithoutExtdInfo(searchStr);
		
		// to define a variable to contain right results
		List<Object[]> rstLs = new ArrayList<Object[]>();
		// to filter the search results to make sure that only the right results([word] or [word + space + number]) will be returned.
		if (tbEnWordInfoWithoutExtdInfo.size() != CommonConsts.COM_ZERO) {
			String word;
			String meaning;
			Object exmpNumObj;
			String exmpNum;
			for (int i = 0; i < tbEnWordInfoWithoutExtdInfo.size(); i ++) {
				word = (String)tbEnWordInfoWithoutExtdInfo.get(i)[SearchEnToCnConsts.INDEX_WORD];
				meaning = (String)tbEnWordInfoWithoutExtdInfo.get(i)[SearchEnToCnConsts.INDEX_MEANING];
				meaning = (meaning == null ? CommonConsts.COM_EMPTY_STRING : meaning.trim());
				exmpNumObj = tbEnWordInfoWithoutExtdInfo.get(i)[SearchEnToCnConsts.INDEX_EXMPNUM];
				exmpNum = (exmpNumObj == null ? CommonConsts.COM_EMPTY_STRING : exmpNumObj.toString());
				// format of the filter is "[word] or [word + space + number]"
				// also,the results whose meaning and example are null at the same time will be filtered
				if (word.trim().matches(searchStr + "( \\d)?")
						&& (!CommonConsts.COM_EMPTY_STRING.equals(meaning) || !CommonConsts.COM_EMPTY_STRING.equals(exmpNum))) {
					rstLs.add(tbEnWordInfoWithoutExtdInfo.get(i));
				}
			}
		}
		return rstLs;
	}
	
	public List<Object[]> searchEnToCnExtdInfo(String searchStr) {
		
		List<Object[]> tbEnExtdWordInfo = tbEnWordDao.getEnExtdWordInfo(searchStr);
		
		return tbEnExtdWordInfo;
	}


	public TbEnWordDao getTbEnWordDao() {
		return tbEnWordDao;
	}


	public void setTbEnWordDao(TbEnWordDao tbEnWordDao) {
		this.tbEnWordDao = tbEnWordDao;
	}
}
