package com.passing.hibernate.beans;

import java.util.Set;



/**
 * TbEnWord generated manually
 */
public class TbEnWord extends AbstractTbEnWord implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TbEnWord() {
	}

	/** full constructor */
	public TbEnWord(int dict_id, int word_id, String word, Set<TbEnWordAttr> tb_en_word_attr, Set<TbEnWordExmp> tb_en_word_exmp) {
		super(dict_id, word_id, word, tb_en_word_attr, tb_en_word_exmp);
	}

}
