package com.passing.hibernate.beans;

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

	// Constructors

	/** default constructor */
	public AbstractTbEnWord() {
	}

	/** full constructor */
	public AbstractTbEnWord(int dict_id, int word_id, String word) {
		this.dict_id = dict_id;
		this.word_id = word_id;
		this.word = word;

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