package com.passing.hibernate.beans;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/**
 * AbstractPassingUser generated manually
 */

public abstract class AbstractTbEnExtdWordExmp implements java.io.Serializable {

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
	protected int extd_word_exmp_num;
	protected String extd_word_exmp_extd_attr;
	protected String extd_word_exmp;
	protected String extd_word_exmp_mean;

	// Constructors

	/** default constructor */
	public AbstractTbEnExtdWordExmp() {
	}

	/** full constructor */
	public AbstractTbEnExtdWordExmp(int dict_id, int word_id, int extd_word_id, String extd_word_ptsp, int extd_word_mean_num, int extd_word_exmp_num, String extd_word_exmp_extd_attr, String extd_word_exmp, String extd_word_exmp_mean) {
		this.dict_id = dict_id;
		this.word_id = word_id;
		this.extd_word_id = extd_word_id;
		this.extd_word_ptsp = extd_word_ptsp;
		this.extd_word_mean_num = extd_word_mean_num;
		this.extd_word_exmp_num = extd_word_exmp_num;
		this.extd_word_exmp_extd_attr = extd_word_exmp_extd_attr;
		this.extd_word_exmp = extd_word_exmp;
		this.extd_word_exmp_mean = extd_word_exmp_mean;

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

	public int getExtd_word_exmp_num() {
		return extd_word_exmp_num;
	}

	public void setExtd_word_exmp_num(int extd_word_exmp_num) {
		this.extd_word_exmp_num = extd_word_exmp_num;
	}

	public String getExtd_word_exmp_extd_attr() {
		return extd_word_exmp_extd_attr;
	}

	public void setExtd_word_exmp_extd_attr(String extd_word_exmp_extd_attr) {
		this.extd_word_exmp_extd_attr = extd_word_exmp_extd_attr;
	}

	public String getExtd_word_exmp() {
		return extd_word_exmp;
	}

	public void setExtd_word_exmp(String extd_word_exmp) {
		this.extd_word_exmp = extd_word_exmp;
	}

	public String getExtd_word_exmp_mean() {
		return extd_word_exmp_mean;
	}

	public void setExtd_word_exmp_mean(String extd_word_exmp_mean) {
		this.extd_word_exmp_mean = extd_word_exmp_mean;
	}

	// over ride equals() and hashCode()
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof TbEnExtdWordExmp)) {
			return false;
		}

		TbEnExtdWordExmp tbEnExtdWordExmp = (TbEnExtdWordExmp) obj;
		return new EqualsBuilder()
				.append(this.dict_id, tbEnExtdWordExmp.getDict_id())
				.append(this.word_id, tbEnExtdWordExmp.getWord_id())
				.append(this.extd_word_id, tbEnExtdWordExmp.getExtd_word_id())
				.append(this.extd_word_ptsp,tbEnExtdWordExmp.getExtd_word_ptsp())
				.append(this.extd_word_mean_num,tbEnExtdWordExmp.getExtd_word_mean_num())
				.append(this.extd_word_exmp_num,tbEnExtdWordExmp.getExtd_word_exmp_num())
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.dict_id).append(this.word_id)
				.append(this.extd_word_id).append(this.extd_word_ptsp)
				.append(this.extd_word_mean_num)
				.append(this.extd_word_exmp_num).toHashCode();
	}
	
}