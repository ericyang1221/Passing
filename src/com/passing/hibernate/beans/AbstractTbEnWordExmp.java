package com.passing.hibernate.beans;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/**
 * AbstractPassingUser generated manually
 */

public abstract class AbstractTbEnWordExmp implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected int dict_id;
	protected int word_id;
	protected String part_of_speech;
	protected int meaning_num;
	protected int example_num;
	protected String example_extd_attr;
	protected String en_exmp;
	protected String exmp_meaning;

	// Constructors

	/** default constructor */
	public AbstractTbEnWordExmp() {
	}

	/** full constructor */
	public AbstractTbEnWordExmp(int dict_id, int word_id, String part_of_speech, int meaning_num,int example_num, String example_extd_attr, String en_exmp, String exmp_meaning) {
		this.dict_id = dict_id;
		this.word_id = word_id;
		this.part_of_speech = part_of_speech;
		this.meaning_num = meaning_num;
		this.example_num = example_num;
		this.example_extd_attr = example_extd_attr;
		this.en_exmp = en_exmp;
		this.exmp_meaning = exmp_meaning;

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

	public int getExample_num() {
		return example_num;
	}

	public void setExample_num(int example_num) {
		this.example_num = example_num;
	}

	public String getExample_extd_attr() {
		return example_extd_attr;
	}

	public void setExample_extd_attr(String example_extd_attr) {
		this.example_extd_attr = example_extd_attr;
	}

	public String getEn_exmp() {
		return en_exmp;
	}

	public void setEn_exmp(String en_exmp) {
		this.en_exmp = en_exmp;
	}

	public String getExmp_meaning() {
		return exmp_meaning;
	}

	public void setExmp_meaning(String exmp_meaning) {
		this.exmp_meaning = exmp_meaning;
	}

	// over ride equals() and hashCode()
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		
		if (!(obj instanceof TbEnWordExmp)) {
			return false;
		}
		
		TbEnWordExmp tbEnWordExmp = (TbEnWordExmp) obj;
		return new EqualsBuilder()
				.append(this.dict_id, tbEnWordExmp.getDict_id())
				.append(this.word_id, tbEnWordExmp.getWord_id())
				.append(this.part_of_speech, tbEnWordExmp.getPart_of_speech())
				.append(this.meaning_num, tbEnWordExmp.getMeaning_num())
				.append(this.example_num, tbEnWordExmp.getExample_num())
				.isEquals();
	}
	
	public int hashCode() {
		return new HashCodeBuilder().append(this.dict_id).append(this.word_id)
				.append(this.part_of_speech).append(this.meaning_num)
				.append(this.example_num).toHashCode();
	}
	
}