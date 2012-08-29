package com.passing.hibernate.beans;


/**
 * AbstractTbEnFrequency generated manually
 */

public abstract class AbstractTbEnFrequency  implements java.io.Serializable {


    // Fields    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int word_id;
	private String word;
	private int frequency;


    // Constructors

    /** default constructor */
    public AbstractTbEnFrequency() {
    }

    /** full constructor */
    public AbstractTbEnFrequency(int word_id, String word, int frequency) {
        this.word_id = word_id;
        this.word = word;
        this.frequency = frequency;
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

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

}