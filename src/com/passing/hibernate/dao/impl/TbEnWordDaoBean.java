package com.passing.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.passing.hibernate.beans.TbEnWord;
import com.passing.hibernate.beans.TbEnWordAttr;
import com.passing.hibernate.dao.TbEnWordDao;

public class TbEnWordDaoBean extends HibernateDaoSupport implements TbEnWordDao {

//	public static Logger log = Logger.getLogger(TbEnWordDaoBean.class);
	
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

//		log.info("SQL = " + sql);
		List<Object[]> list = getHibernateTemplate().find(sql);
		
//		Session session = getHibernateTemplate().getSessionFactory().openSession();
//		Query q= session.createQuery("from TbEnWord where WORD = ?");
//		q.setParameter(0, searchWord);
//		List<TbEnWord> list = q.list();

//		Object[] enw;
//		for (int i = 0; i < list.size(); i ++) {
//			enw = list.get(i);
//			int len = enw.length;
//			for (int j = 0; j < len; j ++) {
//				Object tmp = enw[j];
//				System.out.println(tmp);
//			}
//		}
		
		return list;
	}
	
public List<Object[]> getEnExtdWordInfo(String searchWord) {
		
		String sql = "select "
					+ "ewd.EXTD_WORD "
					+ ",ewda.EXTD_WORD_EXTD_ATTR "
					+ ",ewda.EXTD_WORD_MEAN "
					+ ",ewde.DICT_ID "
					+ ",ewde.WORD_ID "
					+ ",ewde.EXTD_WORD_ID "
					+ ",ewde.EXTD_WORD_PTSP "
					+ ",ewde.EXTD_WORD_MEAN_NUM "
					+ ",ewde.EXTD_WORD_EXMP_NUM "
					+ ",ewde.EXTD_WORD_EXMP_EXTD_ATTR "
					+ ",ewde.EXTD_WORD_EXMP "
					+ ",ewde.EXTD_WORD_EXMP_MEAN "
					+ "from "
					+ "TbEnWord wd, "
					+ "TbEnExtdWord ewd,"
					+ "TbEnExtdWordAttr ewda, "
					+ "TbEnExtdWordExmp ewde "
					+ "where "
					+ "wd.DICT_ID = ewd.DICT_ID "
					+ "and wd.WORD_ID = ewd.WORD_ID "
					+ "and ewd.DICT_ID = ewda.DICT_ID "
					+ "and ewd.WORD_ID = ewda.WORD_ID "
					+ "and ewd.EXTD_WORD_ID = ewda.EXTD_WORD_ID "
					+ "and ewda.DICT_ID = ewde.DICT_ID "
					+ "and ewda.WORD_ID = ewde.WORD_ID "
					+ "and ewda.EXTD_WORD_ID = ewde.EXTD_WORD_ID "
					+ "and ewda.EXTD_WORD_PTSP = ewde.EXTD_WORD_PTSP "
					+ "and ewda.EXTD_WORD_MEAN_NUM = ewde.EXTD_WORD_MEAN_NUM "
					+ "and wd.word = '" + searchWord + "'";

		List<Object[]> list = getHibernateTemplate().find(sql);
		
		return list;
	}

}