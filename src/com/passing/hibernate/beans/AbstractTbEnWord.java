package com.passing.hibernate.beans;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/**
 * AbstractPassingUser generated manually
 */

public abstract class AbstractTbEnWord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected int dict_id;
	protected int word_id;
	protected String word;
	protected Set<TbEnWordAttr> tb_en_word_attr = new HashSet<TbEnWordAttr>();
	protected Set<TbEnWordExmp> tb_en_word_exmp = new HashSet<TbEnWordExmp>();

	// Constructors

	/** default constructor */
	public AbstractTbEnWord() {
	}

	/** full constructor */
	public AbstractTbEnWord(int dict_id, int word_id, String word, Set<TbEnWordAttr> tb_en_word_attr, Set<TbEnWordExmp> tb_en_word_exmp) {
		this.dict_id = dict_id;
		this.word_id = word_id;
		this.word = word;
		this.tb_en_word_attr = tb_en_word_attr;
		this.tb_en_word_exmp = tb_en_word_exmp;

	}

	public int getDict_id() {
		return dict_id;
	}

	public void setDict_id(int dict_id) {
		this.dict_id = dict_id;
	}

	public int getWord_id() {
		return word_id;
	}

	public void setWord_id(int word_id) {
		this.word_id = word_id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	
	public Set<TbEnWordAttr> getTb_en_word_attr() {
		return tb_en_word_attr;
	}

	public void setTb_en_word_attr(Set<TbEnWordAttr> tb_en_word_attr) {
		this.tb_en_word_attr = tb_en_word_attr;
	}

	public Set<TbEnWordExmp> getTb_en_word_exmp() {
		return tb_en_word_exmp;
	}

	public void setTb_en_word_exmp(Set<TbEnWordExmp> tb_en_word_exmp) {
		this.tb_en_word_exmp = tb_en_word_exmp;
	}

	// over ride equals() and hashCode()
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		
		if (!(obj instanceof TbEnWord)) {
			return false;
		}
		
		TbEnWord tbEnWord = (TbEnWord) obj;
		return new EqualsBuilder().append(this.dict_id, tbEnWord.getDict_id()).append(this.word_id, tbEnWord.getWord_id()).isEquals();
	}
	
	public int hashCode() {
		return new HashCodeBuilder().append(this.dict_id).append(this.word_id).toHashCode();
	}
	
}