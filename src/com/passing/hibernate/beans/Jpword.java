package com.passing.hibernate.beans;

import java.util.Set;

/**
 * Jpword generated by MyEclipse Persistence Tools
 */
public class Jpword extends AbstractJpword implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Jpword() {
	}

	/** minimal constructor */
	public Jpword(String kana) {
		super(kana);
	}

	/** full constructor */
	public Jpword(String kana, String word, Set jpwordmeanings,
			Set jpwordremarks) {
		super(kana, word, jpwordmeanings, jpwordremarks);
	}

}
