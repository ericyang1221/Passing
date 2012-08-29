package com.passing.struts.vo;

/**
 * FrequencyVo class
 * @author weishijie
 *
 */
public class FrequencyVo {

	private int wordId;
	private String word;
	private int frequency;
	
	public FrequencyVo(int wordId, String word, int frequency) {
		super();
		this.wordId = wordId;
		this.word = word;
		this.frequency = frequency;
	}

	public int getWordId() {
		return wordId;
	}

	public void setWordId(int wordId) {
		this.wordId = wordId;
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
