package com.passing.struts.vo;

public class EnWordVo {

	private Object word;
	private Object extdAttr;
	private Object mean;
	private Object dictId;
	private Object wordId;
	private Object partOfSpeech;
	private Object meaningNum;
	private Object exampleNum;
	private Object exampleExtdAttr;
	private Object enExmp;
	private Object exmpMeaning;
	
	public EnWordVo(Object word, Object extdAttr, Object mean, Object dictId,
			Object wordId, Object partOfSpeech, Object meaningNum,
			Object exampleNum, Object exampleExtdAttr, Object enExmp,
			Object exmpMeaning) {
		super();
		this.word = word;
		this.extdAttr = extdAttr;
		this.mean = mean;
		this.dictId = dictId;
		this.wordId = wordId;
		this.partOfSpeech = partOfSpeech;
		this.meaningNum = meaningNum;
		this.exampleNum = exampleNum;
		this.exampleExtdAttr = exampleExtdAttr;
		this.enExmp = enExmp;
		this.exmpMeaning = exmpMeaning;
	}
	
	public Object getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public Object getExtdAttr() {
		return extdAttr;
	}
	public void setExtdAttr(String extdAttr) {
		this.extdAttr = extdAttr;
	}
	public Object getMean() {
		return mean;
	}
	public void setMean(String mean) {
		this.mean = mean;
	}
	public Object getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public Object getWordId() {
		return wordId;
	}
	public void setWordId(String wordId) {
		this.wordId = wordId;
	}
	public Object getPartOfSpeech() {
		return partOfSpeech;
	}
	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
	public Object getMeaningNum() {
		return meaningNum;
	}
	public void setMeaningNum(String meaningNum) {
		this.meaningNum = meaningNum;
	}
	public Object getExampleNum() {
		return exampleNum;
	}
	public void setExampleNum(String exampleNum) {
		this.exampleNum = exampleNum;
	}
	public Object getExampleExtdAttr() {
		return exampleExtdAttr;
	}
	public void setExampleExtdAttr(String exampleExtdAttr) {
		this.exampleExtdAttr = exampleExtdAttr;
	}
	public Object getEnExmp() {
		return enExmp;
	}
	public void setEnExmp(String enExmp) {
		this.enExmp = enExmp;
	}
	public Object getExmpMeaning() {
		return exmpMeaning;
	}
	public void setExmpMeaning(String exmpMeaning) {
		this.exmpMeaning = exmpMeaning;
	}
}
