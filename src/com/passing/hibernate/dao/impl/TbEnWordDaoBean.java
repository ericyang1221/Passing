package com.passing.hibernate.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.passing.hibernate.dao.TbEnWordDao;

public class TbEnWordDaoBean extends HibernateDaoSupport implements TbEnWordDao {

	public List<Object[]> getEnWordInfoWithoutExtdInfo(String searchWord) {
		
		String sql = "select "
					+ "enword.word "
					+ ",attr.extd_attr "
					+ ",attr.mean "
					+ ",exmp.dict_id "
					+ ",exmp.word_id "
					+ ",exmp.part_of_speech "
					+ ",exmp.meaning_num "
					+ ",exmp.example_num "
					+ ",exmp.example_extd_attr "
					+ ",exmp.en_exmp "
					+ ",exmp.exmp_meaning "
					+ "from "
					+ "TbEnWord enword "
					+ "left join enword.tb_en_word_attr attr "
					
/** key word "on" is not supported by HQL, it is defined in the hbm file */
//					+ "on enword.dict_id = enword.attr.dict_id "
//					+ "and enword.word_id = enword.attr.word_id "
					+ "left join attr.tb_en_word_exmp exmp "
//					+ "on enword.attr.dict_id = enword.exmp.dict_id "
//					+ "and enword.attr.word_id = enword.exmp.word_id "
//					+ "and enword.attr.part_of_speech = enword.exmp.part_of_speech "
//					+ "and enword.attr.meaning_num = enword.exmp.meaning_num "
					+ "where "
					+ "enword.word = '" + searchWord + "'";

		List<Object[]> list = getHibernateTemplate().find(sql);
		
		return list;
	}
	
public List<Object[]> getEnExtdWordInfo(String searchWord) {
		
		String sql = "select "
					+ "ewd.extd_word "
					+ ",ewda.extd_word_extd_attr "
					+ ",ewda.extd_word_mean "
					+ ",ewde.dict_id "
					+ ",ewde.word_id "
					+ ",ewde.extd_word_id "
					+ ",ewde.extd_word_ptsp "
					+ ",ewde.extd_word_mean_num "
					+ ",ewde.extd_word_exmp_num "
					+ ",ewde.extd_word_exmp_extd_attr "
					+ ",ewde.extd_word_exmp "
					+ ",ewde.extd_word_exmp_mean "
					+ "from "
					+ "TbEnWord wd, "
					+ "TbEnExtdWord ewd,"
					+ "TbEnExtdWordAttr ewda, "
					+ "TbEnExtdWordExmp ewde "
					+ "where "
					+ "wd.dict_id = ewd.dict_id "
					+ "and wd.word_id = ewd.word_id "
					+ "and ewd.dict_id = ewda.dict_id "
					+ "and ewd.word_id = ewda.word_id "
					+ "and ewd.extd_word_id = ewda.extd_word_id "
					+ "and ewda.dict_id = ewde.dict_id "
					+ "and ewda.word_id = ewde.word_id "
					+ "and ewda.extd_word_id = ewde.extd_word_id "
					+ "and ewda.extd_word_ptsp = ewde.extd_word_ptsp "
					+ "and ewda.extd_word_mean_num = ewde.extd_word_mean_num "
					+ "and wd.word = '" + searchWord + "'";

		List<Object[]> list = getHibernateTemplate().find(sql);
		
		return list;
	}

}