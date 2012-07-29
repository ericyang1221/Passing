package com.passing.hibernate.beans;

import java.util.Set;



/**
 * TbEnWord generated manually
 */
public class TbEnWordAttr extends AbstractTbEnWordAttr implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TbEnWordAttr() {
	}

	/** full constructor */
	public TbEnWordAttr(int dict_id, int word_id, String part_of_speech, int meaning_num, String extd_attr, String mean, Set<TbEnWordExmp> tb_en_word_exmp) {
		super(dict_id, word_id, part_of_speech, meaning_num, extd_attr, mean, tb_en_word_exmp);
	}

}
