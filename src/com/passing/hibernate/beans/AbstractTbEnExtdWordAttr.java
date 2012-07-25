package com.passing.hibernate.beans;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/**
 * AbstractPassingUser generated manually
 */

public abstract class AbstractTbEnExtdWordAttr implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected int dict_id;
	protected int word_id;
	protected int extd_word_id;
	protected String extd_word_ptsp;
	protected int extd_word_mean_num;
	protected String extd_word_extd_attr;
	protected String extd_word_mean;

	// Constructors

	/** default constructor */
	public AbstractTbEnExtdWordAttr() {
	}

	/** full constructor */
	public AbstractTbEnExtdWordAttr(int dict_id, int word_id, int extd_word_id, String extd_word_ptsp, int extd_word_mean_num, String extd_word_extd_attr, String extd_word_mean) {
		this.dict_id = dict_id;
		this.word_id = word_id;
		this.extd_word_id = extd_word_id;
		this.extd_word_ptsp = extd_word_ptsp;
		this.extd_word_mean_num = extd_word_mean_num;
		this.extd_word_extd_attr = extd_word_extd_attr;
		this.extd_word_mean = extd_word_mean;

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

	public String getExtd_word_ptsp() {
		return extd_word_ptsp;
	}

	public void setExtd_word_ptsp(String extd_word_ptsp) {
		this.extd_word_ptsp = extd_word_ptsp;
	}

	public int getExtd_word_mean_num() {
		return extd_word_mean_num;
	}

	public void setExtd_word_mean_num(int extd_word_mean_num) {
		this.extd_word_mean_num = extd_word_mean_num;
	}

	public String getExtd_word_extd_attr() {
		return extd_word_extd_attr;
	}

	public void setExtd_word_extd_attr(String extd_word_extd_attr) {
		this.extd_word_extd_attr = extd_word_extd_attr;
	}

	public String getExtd_word_mean() {
		return extd_word_mean;
	}

	public void setExtd_word_mean(String extd_word_mean) {
		this.extd_word_mean = extd_word_mean;
	}

	// over ride equals() and hashCode()
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof TbEnExtdWordAttr)) {
			return false;
		}

		TbEnExtdWordAttr tbEnExtdWordAttr = (TbEnExtdWordAttr) obj;
		return new EqualsBuilder()
				.append(this.dict_id, tbEnExtdWordAttr.getDict_id())
				.append(this.word_id, tbEnExtdWordAttr.getWord_id())
				.append(this.extd_word_id, tbEnExtdWordAttr.getExtd_word_id())
				.append(this.extd_word_ptsp,tbEnExtdWordAttr.getExtd_word_ptsp())
				.append(this.extd_word_mean_num,tbEnExtdWordAttr.getExtd_word_mean_num())
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.dict_id).append(this.word_id)
				.append(this.extd_word_id).append(this.extd_word_ptsp)
				.append(this.extd_word_mean_num).toHashCode();
	}
	
}