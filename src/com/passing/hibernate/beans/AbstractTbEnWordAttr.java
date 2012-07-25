package com.passing.hibernate.beans;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/**
 * AbstractPassingUser generated manually
 */

public abstract class AbstractTbEnWordAttr implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected int dict_id;
	protected int word_id;
	protected String part_of_speech;
	protected int meaning_num;
	protected String extd_attr;
	protected String mean;

	// Constructors

	/** default constructor */
	public AbstractTbEnWordAttr() {
	}

	/** full constructor */
	public AbstractTbEnWordAttr(int dict_id, int word_id, String part_of_speech, int meaning_num, String extd_attr, String mean) {
		this.dict_id = dict_id;
		this.word_id = word_id;
		this.part_of_speech = part_of_speech;
		this.meaning_num = meaning_num;
		this.extd_attr = extd_attr;
		this.mean = mean;

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

	
	public String getPart_of_speech() {
		return part_of_speech;
	}

	public void setPart_of_speech(String part_of_speech) {
		this.part_of_speech = part_of_speech;
	}

	public int getMeaning_num() {
		return meaning_num;
	}

	public void setMeaning_num(int meaning_num) {
		this.meaning_num = meaning_num;
	}

	public String getExtd_attr() {
		return extd_attr;
	}

	public void setExtd_attr(String extd_attr) {
		this.extd_attr = extd_attr;
	}

	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
	}

	// over ride equals() and hashCode()
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		
		if (!(obj instanceof TbEnWordAttr)) {
			return false;
		}
		
		TbEnWordAttr tbEnWordAttr = (TbEnWordAttr) obj;
		return new EqualsBuilder()
				.append(this.dict_id, tbEnWordAttr.getDict_id())
				.append(this.word_id, tbEnWordAttr.getWord_id())
				.append(this.part_of_speech, tbEnWordAttr.getPart_of_speech())
				.append(this.meaning_num, tbEnWordAttr.getMeaning_num())
				.isEquals();
	}
	
	public int hashCode() {
		return new HashCodeBuilder().append(this.dict_id).append(this.word_id)
				.append(this.part_of_speech).append(this.meaning_num)
				.toHashCode();
	}
	
}