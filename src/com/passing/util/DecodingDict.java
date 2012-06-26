package com.passing.util;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecodingDict {

	// The key of dictionary
	private int dictId = 100;
	// The key of word
	private int wordId = 10000000;
	// The key of extend word
	private int extdWordId = 10;
	// meaning number
	private int meaningNum = 100;
	// meaning number of extend word
	private int extdWordMeanNum = 20;
	// example number
	private int exampleNum = 200;
	// example number of extend word
	private int extdWordExmpNum = 30;
	// part of speech
	private String ptsp = "";
	// part of speech of the extend word
	private String ptspOfExtdWord = "";
	// extend word flag to judge if a meaning or example line is for a extend word(0:is not for a extend word; 1:is for a extend word)
	private String extdWordFlg = "0";
	
	private final static String IS_EXTD_WORD = "1";
	
	// The regex for word judge:to match the line which start with tab and has some words followed 
	private final static String WORD_JUDGE_REGEX = "\\t\\S*";
	// The regex for part of speech
	private final static String PTSP_REGEX = "\\[i]\\[trn]\\S*\\[/i]";
	// The regex for English string filter,previous pattern:(<[^<>]*>)|(\\[[^\\[\\]]*])
	private final static String EN_STR_FILTER_REGEX = "\\[[^\\[\\]]*]";
	// The regex for Chinese string filter
	private final static String CH_STR_FILTER_REGEX = "\\[[^\\[\\]]*]";
	// The regex for separating English and Chinese words
	private final static String SPRT_CH_REGEX = "(\\(([^\\x00-\\xff]+[\\W\\w ]*)+\\)([^\\x00-\\xff]+[\\W ]*)+)|(([^\\x00-\\xff]+[\\W\\w ]*)+)";
	// The regex for meaning
	private final static String MEAN_REGEX_1 = "\\[b\\][0-9] ";
	// The regex for meaning
	private final static String MEAN_REGEX_2 = "\\[b\\][0-9]\\[/b\\] ";
	// The regex for example line
	private final static String EXPL_REGEX_1 = "\\[\\*\\]★\\[i\\]";
	// The regex for example line
	private final static String EXPL_REGEX_2 = "\\[\\*\\]★";
	// The regex for example line which has two groups of examples
	private final static String TOW_EXPL_REGEX = " \\* ";
	// The regex for extended word
	private final static String EXTD_WORD_REGEX = "□ \\[b\\]";
	// The regex for part of speech of the extended word
	private final static String PTSP_OF_EXTD_WORD_REGEX = "\\[i\\][a-z]+\\[/i\\]";
	// The regex for marks like [/i] or [/b] and ect
	private final static String MAEKS_REGEX = "\\[/[a-z]+\\]";
	// The regex for marks like .[/trn] or .[/b] and ect
		private final static String MAEKS_REGEX_2 = ".\\[/[a-z]+\\]";
	
	private InputStreamReader sr;
//	private OutputStreamWriter sw;
	private BufferedReader br;
//	private BufferedWriter bw;
//	private FileReader fr;
	private FileWriter fw;
	
	static int count = 0;
	
	public void doWord(String inputFilePath, String outputFilePath) {
		
		try {
			sr = new InputStreamReader(new FileInputStream(inputFilePath), "UTF-8");
//			fr = new FileReader(inputFilePath);
			br = new BufferedReader(sr);
//			sw = new OutputStreamWriter(new FileOutputStream(outputFilePath), "UTF-8");
//			bw = new BufferedWriter(sw);
			fw = new FileWriter(outputFilePath,false);
			
			String tmpStr = "";
			while ((tmpStr = br.readLine()) != null) {
				
				// Matcher used for word Judging
				Matcher wordJudgeMcr = matcherGen(WORD_JUDGE_REGEX,tmpStr);
				
				// The string is a new word
				if (!wordJudgeMcr.find()) {
					
					wordId ++;
					extdWordFlg = "0";
					extdWordId = 10;
					
					System.out.println("insert into TB_EN_WORD(DICT_ID,WORD_ID,WORD) values(" + dictId + "," + wordId + ",'" + enStrFilter(tmpStr) + "');");
					fw.write("insert into TB_EN_WORD(DICT_ID,WORD_ID,WORD) values(" + dictId + "," + wordId + ",'" + enStrFilter(tmpStr) + "');" + "\n");
					count++;
					
				
				// The string is the previous word's sub content
				} else {
					// To delete the tab at the beginning of the string
					String subStr = tmpStr.substring(1);
					
					// To judge if it is a line with part of speech in it ,and separate sub elements to create the sql of TB_EN_WORD_ATTR
					doWordAttr(subStr);
					// To judge if it is a example line and separate sub elements to create the sql of TB_EN_WORD_EXMP
					doWordExmp(subStr);
					// To judge if it is a extend word line and separate sub elements to create the sql of TB_EN_EXTD_WORD
					doExtdWord(subStr);
				}
			}
			
			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * To judge if it is a line with part of speech in it ,then separate sub elements to create the sql of TB_EN_WORD_ATTR
	 * 
	 * @param newStr target string
	 */
	public void doWordAttr(String str) {
		// extended attribute
		String extdAttr = "";
		// Meaning
		String mean = "";
		
		/** generate matchers */
		Matcher ptspMcr = matcherGen(PTSP_REGEX, str);
		Matcher meanMcr1 = matcherGen(MEAN_REGEX_1, str);
		Matcher meanMcr2 = matcherGen(MEAN_REGEX_2, str);
		
		// The string is the line which ptsp is in
		if (ptspMcr.find()) {
			// meaning number
			meaningNum = 100;
			
			ptsp = getPTSP(ptspMcr.group());
			
			//To get the part without ptsp in this line
			String newStr = ptspMcr.replaceAll("");
			if (!newStr.isEmpty()) {
				
				exampleNum = 200;
				
				// To delete the space at the begining of the String
				if (isStartWithMark(' ',newStr)) {
					newStr = newStr.substring(1);
				}
				
				// if the subStr is start with the format like "(idm 涔犺) "
				if (isStartWithMark('(', newStr)) {
					if (isEndWithMark(')', newStr)) {
						extdAttr = newStr;
						newStr = "";
					} else {
						extdAttr = newStr.substring(0, newStr.indexOf(')')+1);
						// To delete the "(idm 涔犺)" and the space after it
						newStr = newStr.substring(newStr.indexOf(')')+1);
						Matcher marksMcr = matcherGen(MAEKS_REGEX, newStr);
						if (!marksMcr.matches()) {
							if (newStr != "") {
								if (isStartWithMark(' ', newStr)) {
									newStr = newStr.substring(1);
								}
							}
							mean = newStr;
						}
					}
					
				// if the subStr is start with the format like "<attrib 浣滃畾璇� "
				} else if (isStartWithMark('<', newStr)) {
					if (isEndWithMark('>', newStr)) {
						extdAttr = newStr;
						newStr = "";
					} else {
						extdAttr = newStr.substring(0, newStr.indexOf('>')+1);
						// To delete the "<attrib 浣滃畾璇�" and the space after it
						newStr = newStr.substring(newStr.indexOf('>')+1);
						if (newStr != "") {
							if (isStartWithMark(' ',newStr)) {
								newStr = newStr.substring(1);
							}
						}
						mean = newStr;
					}
				} else {
					mean = newStr;
				}
//				meanEn = separateEnCh(newStr)[0];
//				meanCh = separateEnCh(newStr)[1];
			}
			
			// this line is for a extend word
			if (IS_EXTD_WORD.equals(extdWordFlg)) {
				
				extdWordMeanNum ++;
				extdWordExmpNum = 30;
				
				System.out.println("insert into TB_EN_EXTD_WORD_ATTR(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_EXTD_ATTR,EXTD_WORD_MEAN_NUM,EXTD_WORD_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "','" + enStrFilter(extdAttr) + "'," + extdWordMeanNum + ",'" + enStrFilter(mean) + "');");
				try {
					fw.write("insert into TB_EN_EXTD_WORD_ATTR(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_EXTD_ATTR,EXTD_WORD_MEAN_NUM,EXTD_WORD_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "','" + enStrFilter(extdAttr) + "'," + extdWordMeanNum + ",'" + enStrFilter(mean) + "');" + "\n");
					count++;
				} catch (IOException e) {
					System.out.println("----------error occured in doPTSP() method------------");
					e.printStackTrace();
				}
			// this line is not for a extend word
			} else {
				meaningNum ++;
				
				System.out.println("insert into TB_EN_WORD_ATTR(DICT_ID,WORD_ID,PART_OF_SPEECH,EXTD_ATTR,MEANING_NUM,MEAN) values(" + dictId + "," + wordId + ",'" + enStrFilter(ptsp) + "','" + enStrFilter(extdAttr) + "'," + meaningNum + ",'" + enStrFilter(mean) + "');");
				try {
					fw.write("insert into TB_EN_WORD_ATTR(DICT_ID,WORD_ID,PART_OF_SPEECH,EXTD_ATTR,MEANING_NUM,MEAN) values(" + dictId + "," + wordId + ",'" + enStrFilter(ptsp) + "','" + enStrFilter(extdAttr) + "'," + meaningNum + ",'" + enStrFilter(mean) + "');" + "\n");
					count ++;
				} catch (IOException e) {
					System.out.println("----------error occured in doPTSP() method------------");
					e.printStackTrace();
				}
			}
			
		// if the string is a whole meaning line, in other words, it is start with "[b]number English words[/b]"
		} else if (meanMcr1.find()) {
			
			exampleNum = 200;
			
			String newStr = ptspMcr.replaceAll("");
			newStr = meanMcr1.replaceAll("");
			if (isStartWithMark(' ', newStr)) {
				newStr = newStr.substring(1);
			}
			// if the subStr is start with the format like "(idm 涔犺) "
			if (isStartWithMark('(', newStr)) {
				extdAttr = newStr.substring(0, newStr.indexOf(')')+1);
				// To delete the "(idm 涔犺)" and the space after it
				newStr = newStr.substring(newStr.indexOf(')')+2);
			// if the subStr is start with the format like "<attrib 浣滃畾璇� "
			} else if (isStartWithMark('<', newStr)) {
				extdAttr = newStr.substring(0, newStr.indexOf('>')+1);
				// To delete the "<attrib 浣滃畾璇�" and the space after it
				newStr = newStr.substring(newStr.indexOf(')')+2);
			}
			mean = newStr;
			
			// this line is for a extend word
			if (IS_EXTD_WORD.equals(extdWordFlg)) {
				
				extdWordMeanNum ++;
				extdWordExmpNum = 30;
				
				System.out.println("insert into TB_EN_EXTD_WORD_ATTR(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_EXTD_ATTR,EXTD_WORD_MEAN_NUM,EXTD_WORD_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "','" + enStrFilter(extdAttr) + "'," + extdWordMeanNum + ",'" + enStrFilter(mean) + "');");
				try {
					fw.write("insert into TB_EN_EXTD_WORD_ATTR(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_EXTD_ATTR,EXTD_WORD_MEAN_NUM,EXTD_WORD_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "','" + enStrFilter(extdAttr) + "'," + extdWordMeanNum + ",'" + enStrFilter(mean) + "');" + "\n");
					count++;
				} catch (IOException e) {
					System.out.println("----------error occured in doPTSP() method------------");
					e.printStackTrace();
				}
			// this line is not for a extend word
			} else {
				meaningNum ++;
				
				System.out.println("insert into TB_EN_WORD_ATTR(DICT_ID,WORD_ID,PART_OF_SPEECH,EXTD_ATTR,MEANING_NUM,MEAN) values(" + dictId + "," + wordId + ",'" + enStrFilter(ptsp) + "','" + enStrFilter(extdAttr) + "'," + meaningNum + ",'" + enStrFilter(mean) + "');");
				try {
					fw.write("insert into TB_EN_WORD_ATTR(DICT_ID,WORD_ID,PART_OF_SPEECH,EXTD_ATTR,MEANING_NUM,MEAN) values(" + dictId + "," + wordId + ",'" + enStrFilter(ptsp) + "','" + enStrFilter(extdAttr) + "'," + meaningNum + ",'" + enStrFilter(mean) + "');" + "\n");
					count++;
				} catch (IOException e) {
					System.out.println("----------error occured in doPTSP() method------------");
					e.printStackTrace();
				}
			}
			
		// if the string is a whole meaning line, in other words, it is start with "[b]number[/b] "
		} else if (meanMcr2.find()) {
			
			exampleNum = 200;
			
			String newStr = ptspMcr.replaceAll("");
			newStr = meanMcr1.replaceAll("");
			if (isStartWithMark(' ', newStr)) {
				newStr = newStr.substring(1);
			}
			// if the subStr is start with the format like "(idm 涔犺) "
			if (isStartWithMark('(', newStr)) {
				extdAttr = newStr.substring(0, newStr.indexOf(')')+1);
				// To delete the "(idm 涔犺)" and the space after it
				newStr = newStr.substring(newStr.indexOf(')')+2);
			// if the subStr is start with the format like "<attrib 浣滃畾璇� "
			} else if (isStartWithMark('<', newStr)) {
				extdAttr = newStr.substring(0, newStr.indexOf('>')+1);
				// To delete the "<attrib 浣滃畾璇�" and the space after it
				newStr = newStr.substring(newStr.indexOf(')')+2);
			}
			mean = newStr;
			
			// this line is for a extend word
			if (IS_EXTD_WORD.equals(extdWordFlg)) {
				
				extdWordMeanNum ++;
				extdWordExmpNum = 30;
				
				System.out.println("insert into TB_EN_EXTD_WORD_ATTR(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_EXTD_ATTR,EXTD_WORD_MEAN_NUM,EXTD_WORD_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "','" + enStrFilter(extdAttr) + "'," + extdWordMeanNum + ",'" + enStrFilter(mean) + "');");
				try {
					fw.write("insert into TB_EN_EXTD_WORD_ATTR(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_EXTD_ATTR,EXTD_WORD_MEAN_NUM,EXTD_WORD_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "','" + enStrFilter(extdAttr) + "'," + extdWordMeanNum + ",'" + enStrFilter(mean) + "');" + "\n");
					count++;
				} catch (IOException e) {
					System.out.println("----------error occured in doPTSP() method------------");
					e.printStackTrace();
				}
			// this line is not for a extend word
			} else {
				meaningNum ++;
				
				System.out.println("insert into TB_EN_WORD_ATTR(DICT_ID,WORD_ID,PART_OF_SPEECH,EXTD_ATTR,MEANING_NUM,MEAN) values(" + dictId + "," + wordId + ",'" + enStrFilter(ptsp) + "','" + enStrFilter(extdAttr) + "'," + meaningNum + ",'" + enStrFilter(mean) + "');");
				try {
					fw.write("insert into TB_EN_WORD_ATTR(DICT_ID,WORD_ID,PART_OF_SPEECH,EXTD_ATTR,MEANING_NUM,MEAN) values(" + dictId + "," + wordId + ",'" + enStrFilter(ptsp) + "','" + enStrFilter(extdAttr) + "'," + meaningNum + ",'" + enStrFilter(mean) + "');" + "\n");
					count++;
				} catch (IOException e) {
					System.out.println("----------error occured in doPTSP() method------------");
					e.printStackTrace();
				}
			}
			
		}
	}
	
	/**
	 * To judge if it is a example line and separate sub elements to create the sql of TB_EN_WORD_EXMP
	 * 
	 * @param str target string
	 */
	public void doWordExmp(String str) {
		// example extended attribute
		String exmpExtdAttr = "";
		// English meaning
		String exmpEn = "";
		// Chinese meaning
		String exmpCh = "";
		
		/** generate matchers */
		Matcher exmpMcr1 = matcherGen(EXPL_REGEX_1, str);
		Matcher exmpMcr2 = matcherGen(EXPL_REGEX_2, str);
		
		if (exmpMcr1.find()) {
			
			String newStr = exmpMcr1.replaceAll("");
			if (isStartWithMark(' ', newStr)) {
				newStr = newStr.substring(1);
			}
			// if the subStr is start with the format like "([i]derog[/i] 璐� "
			if (isStartWithMark('(', newStr)) {
				exmpExtdAttr = newStr.substring(0, newStr.indexOf(')')+1);
				// To delete the "([i]derog[/i] 璐�" and the space after it
				newStr = newStr.substring(newStr.indexOf(')')+2);
			// if the subStr is start with the format like "<attrib 浣滃畾璇� "
			} else if (isStartWithMark('<', newStr)) {
				exmpExtdAttr = newStr.substring(0, newStr.indexOf('>')+1);
				// To delete the "<attrib 浣滃畾璇�" and the space after it
				newStr = newStr.substring(newStr.indexOf(')')+2);
			}
			exmpEn = separateEnCh(newStr)[0];
			exmpCh = separateEnCh(newStr)[1];
			
			if (IS_EXTD_WORD.equals(extdWordFlg)) {
				
				extdWordExmpNum ++;
				
				System.out.println("insert into TB_EN_EXTD_WORD_EXMP(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_MEAN_NUM,EXTD_WORD_EXMP_NUM,EXTD_WORD_EXMP_EXTD_ATTR,EXTD_WORD_EXMP,EXTD_WORD_EXMP_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "'," + extdWordMeanNum + "," + extdWordExmpNum + ",'" + enStrFilter(exmpExtdAttr) + "','" + enStrFilter(exmpEn) + "','" + chStrFilter(exmpCh) + "');");
				try {
					fw.write("insert into TB_EN_EXTD_WORD_EXMP(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_MEAN_NUM,EXTD_WORD_EXMP_NUM,EXTD_WORD_EXMP_EXTD_ATTR,EXTD_WORD_EXMP,EXTD_WORD_EXMP_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "'," + extdWordMeanNum + "," + extdWordExmpNum + ",'" + enStrFilter(exmpExtdAttr) + "','" + enStrFilter(exmpEn) + "','" + chStrFilter(exmpCh) + "');" + "\n");
					count++;
				} catch (IOException e) {
					System.out.println("----------error occured in doWordExmp() method------------");
					e.printStackTrace();
				}
				
			} else {
				
				exampleNum ++;
				
				System.out.println("insert into TB_EN_WORD_EXMP(DICT_ID,WORD_ID,PART_OF_SPEECH,MEANING_NUM,EXAMPLE_NUM,EXAMPLE_EXTD_ATTR,EN_EXMP,EXMP_MEANING) values(" + dictId + "," + wordId + ",'" + enStrFilter(ptsp) + "'," + meaningNum + "," + exampleNum + ",'" + enStrFilter(exmpExtdAttr) + "','" + enStrFilter(exmpEn) + "','" + chStrFilter(exmpCh) + "');");
				try {
					fw.write("insert into TB_EN_WORD_EXMP(DICT_ID,WORD_ID,PART_OF_SPEECH,MEANING_NUM,EXAMPLE_NUM,EXAMPLE_EXTD_ATTR,EN_EXMP,EXMP_MEANING) values(" + dictId + "," + wordId + ",'" + enStrFilter(ptsp) + "'," + meaningNum + "," + exampleNum + ",'" + enStrFilter(exmpExtdAttr) + "','" + enStrFilter(exmpEn) + "','" + chStrFilter(exmpCh) + "');" + "\n");
					count++;
				} catch (IOException e) {
					System.out.println("----------error occured in doWordExmp() method------------");
					e.printStackTrace();
				}
			}
			
		} else if (exmpMcr2.find()) {
			
			
			String newStr = exmpMcr2.replaceAll("");
			
			Matcher towExmpMcr = matcherGen(TOW_EXPL_REGEX, newStr);
			// the newStr has two groups of examples
			if (towExmpMcr.find()) {
				String exmpGrp1 = newStr.substring(0, towExmpMcr.start());
				String exmpGrp2 = newStr.substring(towExmpMcr.start() + 3);
				
				// logic of English example and Chinese meaning separation for exmpGrp1
				if (isStartWithMark(' ', exmpGrp1)) {
					exmpGrp1 = exmpGrp1.substring(1);
				}
				// if the subStr is start with the format like "([i]derog[/i] 璐� "
				if (isStartWithMark('(', exmpGrp1)) {
					exmpExtdAttr = exmpGrp1.substring(0, exmpGrp1.indexOf(')')+1);
					// To delete the "([i]derog[/i] 璐�" and the space after it
					exmpGrp1 = exmpGrp1.substring(exmpGrp1.indexOf(')')+2);
				// if the subStr is start with the format like "<attrib 浣滃畾璇� "
				} else if (isStartWithMark('<', exmpGrp1)) {
					exmpExtdAttr = exmpGrp1.substring(0, exmpGrp1.indexOf('>')+1);
					// To delete the "<attrib 浣滃畾璇�" and the space after it
					exmpGrp1 = exmpGrp1.substring(exmpGrp1.indexOf(')')+2);
				}
				exmpEn = separateEnCh(exmpGrp1)[0];
				exmpCh = separateEnCh(exmpGrp1)[1];
				
				if (IS_EXTD_WORD.equals(extdWordFlg)) {
					
					extdWordExmpNum ++;
					
					System.out.println("insert into TB_EN_EXTD_WORD_EXMP(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_MEAN_NUM,EXTD_WORD_EXMP_NUM,EXTD_WORD_EXMP_EXTD_ATTR,EXTD_WORD_EXMP,EXTD_WORD_EXMP_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "'," + extdWordMeanNum + "," + extdWordExmpNum + ",'" + enStrFilter(exmpExtdAttr) + "','" + enStrFilter(exmpEn) + "','" + chStrFilter(exmpCh) + "');");
					try {
						fw.write("insert into TB_EN_EXTD_WORD_EXMP(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_MEAN_NUM,EXTD_WORD_EXMP_NUM,EXTD_WORD_EXMP_EXTD_ATTR,EXTD_WORD_EXMP,EXTD_WORD_EXMP_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "'," + extdWordMeanNum + "," + extdWordExmpNum + ",'" + enStrFilter(exmpExtdAttr) + "','" + enStrFilter(exmpEn) + "','" + chStrFilter(exmpCh) + "');" + "\n");
						count++;
					} catch (IOException e) {
						System.out.println("----------error occured in doWordExmp() method------------");
						e.printStackTrace();
					}
					
				} else {
					
					exampleNum ++;
					
					System.out.println("insert into TB_EN_WORD_EXMP(DICT_ID,WORD_ID,PART_OF_SPEECH,MEANING_NUM,EXAMPLE_NUM,EXAMPLE_EXTD_ATTR,EN_EXMP,EXMP_MEANING) values(" + dictId + "," + wordId + ",'" + enStrFilter(ptsp) + "'," + meaningNum + "," + exampleNum + ",'" + enStrFilter(exmpExtdAttr) + "','" + enStrFilter(exmpEn) + "','" + chStrFilter(exmpCh) + "');");
					try {
						fw.write("insert into TB_EN_WORD_EXMP(DICT_ID,WORD_ID,PART_OF_SPEECH,MEANING_NUM,EXAMPLE_NUM,EXAMPLE_EXTD_ATTR,EN_EXMP,EXMP_MEANING) values(" + dictId + "," + wordId + ",'" + enStrFilter(ptsp) + "'," + meaningNum + "," + exampleNum + ",'" + enStrFilter(exmpExtdAttr) + "','" + enStrFilter(exmpEn) + "','" + chStrFilter(exmpCh) + "');" + "\n");
						count++;
					} catch (IOException e) {
						System.out.println("----------error occured in doWordExmp() method------------");
						e.printStackTrace();
					}
				}
				
				
				// logic of English example and Chinese meaning separation for exmpGrp2
				if (isStartWithMark(' ', exmpGrp2)) {
					exmpGrp2 = exmpGrp2.substring(1);
				}
				// if the subStr is start with the format like "([i]derog[/i] 璐� "
				if (isStartWithMark('(', exmpGrp2)) {
					exmpExtdAttr = exmpGrp2.substring(0, exmpGrp2.indexOf(')')+1);
					// To delete the "([i]derog[/i] 璐�" and the space after it
					exmpGrp2 = exmpGrp2.substring(exmpGrp2.indexOf(')')+2);
				// if the subStr is start with the format like "<attrib 浣滃畾璇� "
				} else if (isStartWithMark('<', exmpGrp2)) {
					exmpExtdAttr = exmpGrp2.substring(0, exmpGrp2.indexOf('>')+1);
					// To delete the "<attrib 浣滃畾璇�" and the space after it
					exmpGrp2 = exmpGrp2.substring(exmpGrp2.indexOf(')')+2);
				}
				exmpEn = separateEnCh(exmpGrp2)[0];
				exmpCh = separateEnCh(exmpGrp2)[1];
				
				if (IS_EXTD_WORD.equals(extdWordFlg)) {
					
					extdWordExmpNum ++;
					
					System.out.println("insert into TB_EN_EXTD_WORD_EXMP(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_MEAN_NUM,EXTD_WORD_EXMP_NUM,EXTD_WORD_EXMP_EXTD_ATTR,EXTD_WORD_EXMP,EXTD_WORD_EXMP_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "'," + extdWordMeanNum + "," + extdWordExmpNum + ",'" + enStrFilter(exmpExtdAttr) + "','" + enStrFilter(exmpEn) + "','" + chStrFilter(exmpCh) + "');");
					try {
						fw.write("insert into TB_EN_EXTD_WORD_EXMP(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_MEAN_NUM,EXTD_WORD_EXMP_NUM,EXTD_WORD_EXMP_EXTD_ATTR,EXTD_WORD_EXMP,EXTD_WORD_EXMP_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "'," + extdWordMeanNum + "," + extdWordExmpNum + ",'" + enStrFilter(exmpExtdAttr) + "','" + enStrFilter(exmpEn) + "','" + chStrFilter(exmpCh) + "');" + "\n");
						count++;
					} catch (IOException e) {
						System.out.println("----------error occured in doWordExmp() method------------");
						e.printStackTrace();
					}
					
				} else {
					
					exampleNum ++;
					
					System.out.println("insert into TB_EN_WORD_EXMP(DICT_ID,WORD_ID,PART_OF_SPEECH,MEANING_NUM,EXAMPLE_NUM,EXAMPLE_EXTD_ATTR,EN_EXMP,EXMP_MEANING) values(" + dictId + "," + wordId + ",'" + enStrFilter(ptsp) + "'," + meaningNum + "," + exampleNum + ",'" + enStrFilter(exmpExtdAttr) + "','" + enStrFilter(exmpEn) + "','" + chStrFilter(exmpCh) + "');");
					try {
						fw.write("insert into TB_EN_WORD_EXMP(DICT_ID,WORD_ID,PART_OF_SPEECH,MEANING_NUM,EXAMPLE_NUM,EXAMPLE_EXTD_ATTR,EN_EXMP,EXMP_MEANING) values(" + dictId + "," + wordId + ",'" + enStrFilter(ptsp) + "'," + meaningNum + "," + exampleNum + ",'" + enStrFilter(exmpExtdAttr) + "','" + enStrFilter(exmpEn) + "','" + chStrFilter(exmpCh) + "');" + "\n");
						count++;
					} catch (IOException e) {
						System.out.println("----------error occured in doWordExmp() method------------");
						e.printStackTrace();
					}
					
				}
			
			// the newStr has only one example group
			} else {
				if (isStartWithMark(' ', newStr)) {
					newStr = newStr.substring(1);
				}
				// if the subStr is start with the format like "([i]derog[/i] 璐� "
				if (isStartWithMark('(', newStr)) {
					exmpExtdAttr = newStr.substring(0, newStr.indexOf(')')+1);
					// To delete the "([i]derog[/i] 璐�" and the space after it
					newStr = newStr.substring(newStr.indexOf(')')+2);
				// if the subStr is start with the format like "<attrib 浣滃畾璇� "
				} else if (isStartWithMark('<', newStr)) {
					exmpExtdAttr = newStr.substring(0, newStr.indexOf('>')+1);
					// To delete the "<attrib 浣滃畾璇�" and the space after it
					newStr = newStr.substring(newStr.indexOf(')')+2);
				}
				exmpEn = separateEnCh(newStr)[0];
				exmpCh = separateEnCh(newStr)[1];
				
				if (IS_EXTD_WORD.equals(extdWordFlg)) {
					
					extdWordExmpNum ++;
					
					System.out.println("insert into TB_EN_EXTD_WORD_EXMP(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_MEAN_NUM,EXTD_WORD_EXMP_NUM,EXTD_WORD_EXMP_EXTD_ATTR,EXTD_WORD_EXMP,EXTD_WORD_EXMP_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "'," + extdWordMeanNum + "," + extdWordExmpNum + ",'" + enStrFilter(exmpExtdAttr) + "','" + enStrFilter(exmpEn) + "','" + chStrFilter(exmpCh) + "');");
					try {
						fw.write("insert into TB_EN_EXTD_WORD_EXMP(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_MEAN_NUM,EXTD_WORD_EXMP_NUM,EXTD_WORD_EXMP_EXTD_ATTR,EXTD_WORD_EXMP,EXTD_WORD_EXMP_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "'," + extdWordMeanNum + "," + extdWordExmpNum + ",'" + enStrFilter(exmpExtdAttr) + "','" + enStrFilter(exmpEn) + "','" + chStrFilter(exmpCh) + "');" + "\n");
						count++;
					} catch (IOException e) {
						System.out.println("----------error occured in doWordExmp() method------------");
						e.printStackTrace();
					}
					
				} else {
					
					exampleNum ++;
					
					System.out.println("insert into TB_EN_WORD_EXMP(DICT_ID,WORD_ID,PART_OF_SPEECH,MEANING_NUM,EXAMPLE_NUM,EXAMPLE_EXTD_ATTR,EN_EXMP,EXMP_MEANING) values(" + dictId + "," + wordId + ",'" + enStrFilter(ptsp) + "'," + meaningNum + "," + exampleNum + ",'" + enStrFilter(exmpExtdAttr) + "','" + enStrFilter(exmpEn) + "','" + chStrFilter(exmpCh) + "');");
					try {
						fw.write("insert into TB_EN_WORD_EXMP(DICT_ID,WORD_ID,PART_OF_SPEECH,MEANING_NUM,EXAMPLE_NUM,EXAMPLE_EXTD_ATTR,EN_EXMP,EXMP_MEANING) values(" + dictId + "," + wordId + ",'" + enStrFilter(ptsp) + "'," + meaningNum + "," + exampleNum + ",'" + enStrFilter(exmpExtdAttr) + "','" + enStrFilter(exmpEn) + "','" + chStrFilter(exmpCh) + "');" + "\n");
						count++;
					} catch (IOException e) {
						System.out.println("----------error occured in doWordExmp() method------------");
						e.printStackTrace();
					}
					
				}
				
			}
			
		}
	}
	
	/**
	 * To judge if it is a extend word line and separate sub elements to create the sql of TB_EN_EXTD_WORD
	 * and if the line include the other attributes of extend word, also create the sql of TB_EN_EXTD_WORD_ATTR
	 * otherwise do not create the sql of TB_EN_EXTD_WORD_ATTR
	 * 
	 * @param str target string
	 */
	public void doExtdWord(String str) {
		// extend word
		String extdWord = "";
		
		Matcher extdWordMcr = matcherGen(EXTD_WORD_REGEX, str);
		if (extdWordMcr.find()) {
			
			extdWordMeanNum = 20;
			extdWordFlg = "1";
			extdWordId ++;
			extdWordExmpNum = 30;
			
			// To delete the "□ [b]" at the beginning of the line
			String subStr = extdWordMcr.replaceAll("");
			int index = subStr.indexOf("[/b]");
			extdWord = subStr.substring(0, index);
			
			System.out.println("insert into TB_EN_EXTD_WORD(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(extdWord) + "');");
			try {
				fw.write("insert into TB_EN_EXTD_WORD(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(extdWord) + "');" + "\n");
				count++;
			} catch (IOException e) {
				System.out.println("----------error occured in doExtdWord() method------------");
				e.printStackTrace();
			}
			
			// the left part of the line without extend word
			String leftStr = subStr.substring(index + 4);
			if (!".".equals(leftStr)) {
				if (!leftStr.isEmpty()) {
					// To delete the space in the beginning of the leftStr
					leftStr = leftStr.substring(1);
					
					// part of speech of the extend word
					ptspOfExtdWord = "";
					if (isStartWithMark('(', leftStr)) {
						leftStr = enStrFilter(leftStr);
						
						// extend attribute of the extend word
						String extdWordExtdAttr = "";
						// meaning number of the extend word
						extdWordMeanNum ++;
						// English meaning of the extend word
						String extdWordMean = "";
						
						extdWordExmpNum = 30;
						
						if (isEndWithMark(')', leftStr) || ')' == leftStr.charAt(leftStr.length()-2)) {
							extdWordMean = leftStr;
							leftStr = "";
						} else {
							extdWordExtdAttr = leftStr.substring(0, leftStr.indexOf(')') + 1);
							extdWordMean = leftStr.replace(extdWordExtdAttr, "");
							leftStr = "";
						}
						System.out.println("insert into TB_EN_EXTD_WORD_ATTR(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_EXTD_ATTR,EXTD_WORD_MEAN_NUM,EXTD_WORD_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "','" + enStrFilter(extdWordExtdAttr) + "'," + extdWordMeanNum + ",'" + enStrFilter(extdWordMean) + "');");
						try {
							fw.write("insert into TB_EN_EXTD_WORD_ATTR(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_EXTD_ATTR,EXTD_WORD_MEAN_NUM,EXTD_WORD_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "','" + enStrFilter(extdWordExtdAttr) + "'," + extdWordMeanNum + ",'" + enStrFilter(extdWordMean) + "');" + "\n");
							count++;
						} catch (IOException e) {
							System.out.println("----------error occured in doPTSP() method------------");
							e.printStackTrace();
						}
					}
					Matcher ptspOfExtdWordMcr = matcherGen(PTSP_OF_EXTD_WORD_REGEX, leftStr);
					if (ptspOfExtdWordMcr.find()) {
						ptspOfExtdWord = ptspOfExtdWordMcr.group();
						
						String meanStr = ptspOfExtdWordMcr.replaceFirst("");
						// if the left part without ptspOfExtdWord is not null, separate the elements and create sql for TB_EN_EXTD_WORD_ATTR,otherwise do not create sql
						if (!meanStr.isEmpty()) {
							
							// extend attribute of the extend word
							String extdWordExtdAttr = "";
							// meaning number of the extend word
							extdWordMeanNum ++;
							// English meaning of the extend word
							String extdWordMean = "";
							
							extdWordExmpNum = 30;
							
							// To delete the space at the beginning of the String
							if (isStartWithMark(' ',meanStr)) {
								meanStr = meanStr.substring(1);
							}
							
							// if the subStr is start with the format like "(idm 涔犺) "
							if (isStartWithMark('(', meanStr)) {
								extdWordExtdAttr = meanStr.substring(0, meanStr.indexOf(')')+1);
								String subStr2 = enStrFilter(meanStr.replace(extdWordExtdAttr, ""));
								if (!subStr2.isEmpty() && !".".equals(subStr2) && !" ".equals(subStr2)) {
									// To delete the "(idm 涔犺)" and the space after it
									meanStr = subStr2;
								} else {
									meanStr = "";
								}
								
							// if the subStr is start with the format like "<attrib 浣滃畾璇� "
							} else if (isStartWithMark('<', meanStr)) {
								extdWordExtdAttr = meanStr.substring(0, meanStr.indexOf('>')+1);
								
								String subStr2 = meanStr.replace(extdWordExtdAttr, "");
								Matcher markMcr = matcherGen(MAEKS_REGEX_2, subStr2);
								if (!markMcr.matches()) {
									if (!meanStr.replace(extdWordExtdAttr, "").isEmpty()) {
										// To delete the "<attrib 浣滃畾璇�" and the space after it
										meanStr = meanStr.substring(meanStr.indexOf('>')+2);
									} else {
										meanStr = "";
									}
								} else {
									meanStr = "";
								}
								
							}
							extdWordMean = meanStr;
							
							System.out.println("insert into TB_EN_EXTD_WORD_ATTR(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_EXTD_ATTR,EXTD_WORD_MEAN_NUM,EXTD_WORD_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "','" + enStrFilter(extdWordExtdAttr) + "'," + extdWordMeanNum + ",'" + enStrFilter(extdWordMean) + "');");
							try {
								fw.write("insert into TB_EN_EXTD_WORD_ATTR(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_EXTD_ATTR,EXTD_WORD_MEAN_NUM,EXTD_WORD_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "','" + enStrFilter(extdWordExtdAttr) + "'," + extdWordMeanNum + ",'" + enStrFilter(extdWordMean) + "');" + "\n");
								count++;
							} catch (IOException e) {
								System.out.println("----------error occured in doPTSP() method------------");
								e.printStackTrace();
							}
							
						}
					} else {
						Matcher markMcr = matcherGen(MAEKS_REGEX, leftStr);
						if (!markMcr.matches()) {
							String extdWordExtdAttr = "";
							String extdWordMean = leftStr;
							
							// meaning number of the extend word
							extdWordMeanNum ++;
							
							extdWordExmpNum = 30;
							
							System.out.println("insert into TB_EN_EXTD_WORD_ATTR(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_EXTD_ATTR,EXTD_WORD_MEAN_NUM,EXTD_WORD_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "','" + enStrFilter(extdWordExtdAttr) + "'," + extdWordMeanNum + ",'" + enStrFilter(extdWordMean) + "');");
							try {
								fw.write("insert into TB_EN_EXTD_WORD_ATTR(DICT_ID,WORD_ID,EXTD_WORD_ID,EXTD_WORD_PTSP,EXTD_WORD_EXTD_ATTR,EXTD_WORD_MEAN_NUM,EXTD_WORD_MEAN) values(" + dictId + "," + wordId + "," + extdWordId + ",'" + enStrFilter(ptspOfExtdWord) + "','" + enStrFilter(extdWordExtdAttr) + "'," + extdWordMeanNum + ",'" + enStrFilter(extdWordMean) + "');" + "\n");
								count++;
							} catch (IOException e) {
								System.out.println("----------error occured in doPTSP() method------------");
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
		
	/**
	 * To generate matchers
	 * 
	 * @param regex The regex to match with
	 * @param str String that want to be matched
	 * @return Matcher
	 */
	private Matcher matcherGen(String regex, String str) {
		Pattern pt = Pattern.compile(regex);
		Matcher mc = pt.matcher(str);
		return mc;
	}
	
	/**
	 * To get part of speech from string like "[i][trn]...[/i]"
	 * 
	 * @param str String from which ptsp can be gotten
	 * @return String part of speech
	 */
	private String getPTSP(String str) {
		return str.substring(8, str.length()-4);
	}
	
	/**
	 * Method you must call before you write a English string to sql,
	 * it can delete useless tags left in the string,such as []
	 * and if the string is a null,this method will write a space in
	 * to make sure no null is write into sql
	 * and if the string has "'" in it, it will plus a "'" before "'"
	 * to make sure the "'" won't call a false in sql
	 * 
	 * @param str 
	 * @return String The final string you should write into sql
	 */
	private String enStrFilter(String str) {
		if (str.isEmpty()) {
			str = " ";
		} else {
			Matcher mcr = matcherGen(EN_STR_FILTER_REGEX, str);
			while (mcr.find()) {
				str = mcr.replaceAll("");
			}
			
			// subStr is start with a space
			if (isStartWithMark(' ',str)) {
				str = str.substring(1);
			}
			// To replace "'" with "''"
			str = str.replaceAll("'", "''");
		}
		return str;
	}
	
	/**
	 * Method you must call before you write a Chinese string to sql,
	 * it can delete useless tags left in the string,such as <>,[]
	 * and if the string is a null,this method will write a space in
	 * to make sure no null is write into sql
	 * 
	 * @param str 
	 * @return String The final string you should write into sql
	 */
	private String chStrFilter(String str) {
		if (str.isEmpty()) {
			str = " ";
		} else {
			Matcher mcr = matcherGen(CH_STR_FILTER_REGEX, str);
			while (mcr.find()) {
				str = mcr.replaceAll("");
			}
			
			// subStr is start with a space
			if (str != "") {
				if (isStartWithMark(' ',str)) {
					str = str.substring(1);
				}
			}
			
		}
		return str;
	}
	
	
	/**
	 * To judge if the string is start with a specified char
	 * 
	 * @param ch specified ch or mark
	 * @param str target string
	 * @return boolean
	 */
	private boolean isStartWithMark(char ch, String str) {
		boolean rst = false;
			if (str.charAt(0) == ch) {
				rst = true;
			}
		return rst;
	}
	
	/**
	 *  To judge if the string is end with a specified char
	 * 
	 * @param ch specified char or mark
	 * @param str target string
	 * @return boolean
	 */
	private boolean isEndWithMark(char ch, String str) {
		boolean rst = false;
			if (str.charAt(str.length()-1) == ch) {
				rst = true;
			}
		return rst;
	}
	
	/**
	 * To separate the English sentence and Chinese sentence from a string
	 * 
	 * @param str target string
	 * @return String[]{stcEn, stcCh}
	 */
	private String[] separateEnCh(String str) {
		String stcCh = "";
		String stcEn = "";
		
		Matcher chMcr = matcherGen(SPRT_CH_REGEX, str);
		if (chMcr.find()) {
			stcCh = chMcr.group();
			String tmpStr = chMcr.replaceAll("");
			if (!tmpStr.isEmpty()) {
				if (isEndWithMark(' ', tmpStr)) {
					stcEn = tmpStr.substring(0, tmpStr.length()-1);
				} else {
					stcEn = tmpStr;
				}
			}
			
		} else {
			stcEn = str;
		}
		return new String[]{stcEn, stcCh};
	}
	
	public static void main(String[] args) {
		DecodingDict dd = new DecodingDict();
		
//		String str = "[i][trn]n[/i]";
//		System.out.println(IsStartWithTab(str));

//		Matcher mc = dd.matcherGen(PTSP_REGEX,str);
//		while (mc.find()) {
//			System.out.println(mc.group());
//		}
//		String str1 = "[i][trn]n[/i]";
//		System.out.println(dd.getPTSP(str1));
		
//		String str2 = "[i][trn]pron[/i] ([i]infml[/i] 鍙� = [b]them[/b]: [i]Don't let 'em get away![/i] 鍒浠栦滑璺戞帀![/trn]";
//		System.out.println(dd.enStrFilter(str2));
		
//		String str3 = "";
//		System.out.println(dd.isStartWithMark(' ',str3));
		
//		String str4 = "(sfa sfa asf) aaa aaa aaa";
//		System.out.println(str4.substring(0, str4.indexOf(')')+1));
		
//		String str5 = "Success in making money is not always a good criterion of success in life.[/i] 鑳芥專閽卞苟涓嶄竴瀹氭槸琛￠噺浜虹敓骞哥鐨勫彲闈犳爣鍑�[/*]";
//		Matcher mc = dd.matcherGen(SPRT_CH_REGEX, str5);
//		while (mc.find()){
//			System.out.println(mc.group());
//		}
//		String str6 = "[b]2[/b] <attrib 浣滃畾璇� of the art of making judgements on literature, art, etc 锛堟枃瀛︺� 鑹烘湳绛夛級璇勮鎵嬫硶鐨�";
//		Matcher meanMcr = dd.matcherGen(MEAN_REGEX_2, str6);
//		while (meanMcr.find()) {
//			System.out.println(meanMcr.group());
//		}
		
//		String str7 = "([i]derog[/i] 璐� [i]Why are you always so critical?[/i] 浣犳�涔堣�鏄繖鏍峰惞姣涙眰鐤� * ([i]approv[/i] 瑜� [i]Try to develop a more critical attitude, instead of accepting everything at face value.[/i] 瑕佸浼氬涓�垏浜嬬墿涓�笣涓嶈嫙, 鑰屼笉瑕佹敞閲嶈〃闈㈢幇璞�[/*]";
//		Matcher towExmpMcr = dd.matcherGen(TOW_EXPL_REGEX, str7);
//		while (towExmpMcr.find()) {
//			String exmpGrp1 = str7.substring(0, towExmpMcr.start());
//			String exmpGrp2 = str7.substring(towExmpMcr.start() + 3);
//			System.out.println(exmpGrp1 + "\n" + exmpGrp2);
//		}
//		String str8 = "鈻�[b]&dn;critical `path analysis[/b] the study of a set of operations (eg in building a ship) to decide the quickest and most efficient order in which to do them 鍏抽敭閫斿緞鍒嗘瀽娉曪紙鍒嗘瀽鏁翠綋宸ヤ綔浠ュ埗瀹氬嚭鏈�揩銆�鏈�湁鏁堢殑绋嬪簭锛�";
//		Matcher extdWordMcr = dd.matcherGen(EXTD_WORD_REGEX, str8);
//		while (extdWordMcr.find()) {
//			System.out.println(extdWordMcr.group());
//		}
//		String str9 = "鈻�[b]critically[/b] [i]adv[/i]: [i]speak critically of sb[/i] 闈炶鏌愪汉";
//		Matcher extdWordMcr = dd.matcherGen(PTSP_OF_EXTD_WORD_REGEX, str9);
//		while (extdWordMcr.find()) {
//			System.out.println(extdWordMcr.group());
//		}
		
//		dd.doWord("/home/lovelease/Desktop/Dict.txt", "/home/lovelease/Desktop/EnDict.txt");
//		dd.doWord("/home/lovelease/Desktop/test.txt", "/home/lovelease/Desktop/test_copy.txt");
		dd.doWord("C:/Users/lovelease/Desktop/Dict.TXT", "C:/Users/lovelease/Desktop/EnDict.TXT");
		System.out.println(count);
//		dd.doWord("C:/Users/lovelease/Desktop/test.TXT", "C:/Users/lovelease/Desktop/EnTest.TXT");
	}
}
