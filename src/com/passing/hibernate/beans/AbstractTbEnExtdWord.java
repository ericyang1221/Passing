package com.passing.hibernate.beans;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/**
 * AbstractPassingUser generated manually
 */

public abstract class AbstractTbEnExtdWord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected int dict_id;
	protected int word_id;
	protected int extd_word_id;
	protected String extd_word;

	// Constructors

	/** default constructor */
	public AbstractTbEnExtdWord() {
	}

	/** full constructor */
	public AbstractTbEnExtdWord(int dict_id, int word_id, int extd_word_id, String extd_word) {
		this.dict_id = dict_id;
		this.word_id = word_id;
		this.extd_word_id = extd_word_id;
		this.extd_word = extd_word;

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

	
	public int getExtd_word_id() {
		return extd_word_id;
	}

	public void setExtd_word_id(int extd_word_id) {
		this.extd_word_id = extd_word_id;
	}

	public String getExtd_word() {
		return extd_word;
	}

	public void setExtd_word(String extd_word) {
		this.extd_word = extd_word;
	}

	// over ride equals() and hashCode()
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof TbEnExtdWord)) {
			return false;
		}

		TbEnExtdWord tbEnExtdWord = (TbEnExtdWord) obj;
		return new EqualsBuilder()
				.append(this.dict_id, tbEnExtdWord.getDict_id())
				.append(this.word_id, tbEnExtdWord.getWord_id())
				.append(this.extd_word_id, tbEnExtdWord.getExtd_word_id())
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.dict_id).append(this.word_id)
				.append(this.extd_word_id).toHashCode();
	}
	
}