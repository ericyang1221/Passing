package com.passing.hibernate.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.passing.hibernate.dao.TbEnWordDao;

public class TbEnWordDaoBean extends HibernateDaoSupport implements TbEnWordDao {

	public List<Object[]> getEnWordInfoWithoutExtdInfo(String searchWord) {
		
		/** HQL的join用法与SQL不同，它不支持on关键字，所以语句上也与普通的SQL不同，
		 * 它的on条件实际上是通过标签定义在hbm文件中的，以表示两个表之间的关联关系，
		 * 如下就是一句正确的HQL语句（hbm文件已经配好，可以正确执行）  
		 * ==============================================================================START
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
					
					// 以下的两个表之间的连接条件都被定义在hbm文件中，在HQL中不能使用
//					+ "on enword.dict_id = enword.attr.dict_id "
//					+ "and enword.word_id = enword.attr.word_id "
					+ "left join attr.tb_en_word_exmp exmp "
//					+ "on enword.attr.dict_id = enword.exmp.dict_id "
//					+ "and enword.attr.word_id = enword.exmp.word_id "
//					+ "and enword.attr.part_of_speech = enword.exmp.part_of_speech "
//					+ "and enword.attr.meaning_num = enword.exmp.meaning_num "
					+ "where "
					+ "enword.word = '" + searchWord + "'";
		 =================================================================================== END */
		
		/** HQL同样支持本地SQL语句，由于HQL的join比较繁琐，此处使用如下的简单SQL来查询  */
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
				+ "TbEnWord enword, "
				+ "TbEnWordAttr attr, "
				+ "TbEnWordExmp exmp "
				+ "where "
				+ "enword.dict_id = attr.dict_id "
				+ "and enword.word_id = attr.word_id "
				+ "and attr.dict_id = exmp.dict_id "
				+ "and attr.word_id = exmp.word_id "
				+ "and attr.part_of_speech = exmp.part_of_speech "
				+ "and attr.meaning_num = exmp.meaning_num "
				+ "and enword.word like '" + searchWord + "%'";

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