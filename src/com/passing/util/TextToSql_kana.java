package com.passing.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TextToSql_kana {

	InputStreamReader fr;
	OutputStreamWriter fw;
	BufferedReader br;
	BufferedWriter bw;

	String readbuffer = "";
	String writebuffer = "";
	String currentKanaStr = "";
	String currentMeaningStr = "";
	String currentWordStr = "";
	String currentExampleStr = "";
	String currentExampleMeaningStr = "";
	int wordrow = 1;
	int meaningrow = 1;
	int examplerow = 1;
	int remarkrow = 1;

	public void doTextToSql(String inputFilePath, String outputFilePath) {
		try {
			fr = new InputStreamReader(new FileInputStream(inputFilePath),
					"UTF-8");
			fw = new OutputStreamWriter(new FileOutputStream(outputFilePath),
					"UTF-8");
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);

			if (br.ready()) {
				readbuffer = br.readLine().substring(1);
				doWord();
			}

			fr.close();
			br.close();
			fw.flush();
			bw.flush();
			bw.close();
			fw.close();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void doWord() throws Exception {
		while (br.ready()) {
			if (readbuffer.charAt(0) != '\t') {
				currentKanaStr = readbuffer.trim();
				if (currentKanaStr.charAt(0) == '‐') {
					currentKanaStr = currentKanaStr.substring(1);
				}
				if(currentKanaStr.charAt(currentKanaStr.length()-1) > '0' && currentKanaStr.charAt(currentKanaStr.length()-1) <= '9'){
					currentKanaStr = currentKanaStr.substring(0, currentKanaStr.length()-1);
				}
				try {
					if (br.ready()) {
						readbuffer = br.readLine();
						if (readbuffer.indexOf("\t[trn]") > 0) {
							readbuffer = readbuffer.substring(readbuffer
									.indexOf("\t[trn]")
									+ "\t[trn]".length());
							readbuffer = "\t" + readbuffer;
						} else if (readbuffer.indexOf("[trn]") > 0
								&& !readbuffer.endsWith("[trn]")) {
							readbuffer = readbuffer.substring(readbuffer
									.indexOf("[trn]")
									+ "[trn]".length());
							readbuffer = "\t" + readbuffer;
						}
						if (readbuffer.indexOf('【') != -1) {
							if (readbuffer.indexOf('【') != -1) {
								currentWordStr = readbuffer.substring(
										readbuffer.indexOf('【') + 1, readbuffer
												.indexOf('】'));
							}
							writebuffer = "insert into JPWORD values('"
									+ wordrow + "','" + currentKanaStr + "','"
									+ currentWordStr + "');";
							System.out.println(writebuffer);
							bw.write(writebuffer);
							bw.newLine();
							if (br.ready()) {
								readbuffer = br.readLine();
							} else {
								readbuffer = null;
							}
						} else {
							writebuffer = "insert into JPWORD values('"
									+ wordrow + "','" + currentKanaStr
									+ "','');";
							System.out.println(writebuffer);
							bw.write(writebuffer);
							bw.newLine();
						}
						doMeaning();
						wordrow++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void doMeaning() throws Exception {
		if (readbuffer.indexOf("\t[trn]") > 0) {
			readbuffer = readbuffer.substring(readbuffer.indexOf("\t[trn]")
					+ "\t[trn]".length());
			readbuffer = '\t' + readbuffer;
		}
		if ("\t（".equals(readbuffer.substring(0, 2))) {
			while ("\t（".equals(readbuffer.substring(0, 2))) {
				currentMeaningStr = readbuffer.substring(4);
				if (currentMeaningStr.indexOf("[/trn]") > 0) {
					currentMeaningStr = currentMeaningStr.substring(0,
							currentMeaningStr.indexOf("[/trn]"));
				}
				writebuffer = "insert into JPWORDMEANING values('" + meaningrow
						+ "','" + wordrow + "','" + currentMeaningStr
						+ "','');";
				System.out.println(writebuffer);
				bw.write(writebuffer);
				bw.newLine();
				if (br.ready()) {
					readbuffer = br.readLine();
					if (readbuffer.charAt(0) == '\t'
							&& readbuffer.charAt(1) != '（'
							&& !"\t[*]".equals(readbuffer.substring(0, 4))
							&& readbuffer.charAt(1) != '『'
							&& readbuffer.charAt(1) != '★') {
						readbuffer = ("\t[*]" + readbuffer.substring(1) + "[/*]")
								.replaceFirst("\\ ", "／");
					}
					doExample();
					meaningrow++;
				} else {
					break;
				}
			}

		} else {
			currentMeaningStr = readbuffer.substring(1);
			if (currentMeaningStr.indexOf("[/trn]") > 0) {
				currentMeaningStr = currentMeaningStr.substring(0,
						currentMeaningStr.indexOf("[/trn]"));
			}
			writebuffer = "insert into JPWORDMEANING values('" + meaningrow
					+ "','" + wordrow + "','" + currentMeaningStr + "','');";
			System.out.println(writebuffer);
			bw.write(writebuffer);
			bw.newLine();
			if (br.ready()) {
				readbuffer = br.readLine();
				if (readbuffer.charAt(0) == '\t' && readbuffer.charAt(1) != '（'
						&& !"\t[*]".equals(readbuffer.substring(0, 4))
						&& readbuffer.charAt(1) != '『'
						&& readbuffer.charAt(1) != '★') {
					readbuffer = ("\t[*]" + readbuffer.substring(1) + "[/*]")
							.replaceFirst("\\ ", "／");
				}
				doExample();
				meaningrow++;
			} 
		}
	}

	public void doExample() throws Exception {
		try {
			while ("\t[*]".equals(readbuffer.substring(0, 4))) {
				currentExampleStr = readbuffer.substring(4, readbuffer
						.indexOf('／'));
				currentExampleMeaningStr = readbuffer.substring(readbuffer
						.indexOf('／') + 1, readbuffer.indexOf("[/*]"));
				if (currentExampleMeaningStr.indexOf("[/trn]") > 0) {
					currentExampleMeaningStr = currentExampleMeaningStr
							.substring(0, currentExampleMeaningStr
									.indexOf("[/trn]"));
				}
				writebuffer = "insert into JPWORDEXAMPLE values('" + examplerow
						+ "','" + meaningrow + "','" + currentExampleStr
						+ "','" + currentExampleMeaningStr + "');";
				System.out.println(writebuffer);
				bw.write(writebuffer);
				bw.newLine();
				if (br.ready()) {
					readbuffer = br.readLine();
					if (readbuffer.charAt(0) == '\t'
							&& readbuffer.charAt(1) != '（'
							&& !"\t[*]".equals(readbuffer.substring(0, 4))
							&& readbuffer.charAt(1) != '『'
							&& readbuffer.charAt(1) != '★'
							&& readbuffer.charAt(1) != '▼') {
						readbuffer = ("\t[*]" + readbuffer.substring(1) + "[/*]")
								.replaceFirst("\\ ", "／");
					}
					examplerow++;
				} else {
					break;
				}
			}
			if ("\t『語法』".equals(readbuffer.substring(0, "\t『語法』".length()))
					|| "\t『比較』".equals(readbuffer.substring(0, "\t『比較』".length()))
					|| "\t★".equals(readbuffer.substring(0, "\t★".length()))
					|| "\t『参考』".equals(readbuffer.substring(0, "\t『参考』".length()))
					|| "\t▼".equals(readbuffer.substring(0, "\t▼".length()))) {
				String remarkStr = readbuffer;
				readbuffer = br.readLine();
				while(readbuffer.charAt(0) == '\t' && readbuffer.charAt(1) != '（' && !"[*]".equals(readbuffer.substring(1,"[*]".length()))){
					remarkStr += readbuffer;
					readbuffer = br.readLine();
				}
				remarkStr = remarkStr.substring(1);
				if(remarkStr.indexOf("[/trn]") > 0){
					remarkStr = remarkStr.substring(0, remarkStr.indexOf("[/trn]"));
				}
				if(remarkStr.indexOf("[/*]") > 0){
					remarkStr = remarkStr.substring(0, remarkStr.indexOf("[/*]"));
				}
				writebuffer = "insert into JPWORDREMARK values('"+remarkrow+"','"+wordrow+"','"+remarkStr+"');";
				System.out.println(writebuffer);
				bw.write(writebuffer);
				bw.newLine();
				remarkrow++;
			}
		} catch (StringIndexOutOfBoundsException e) {
			// System.out.println("readbuffer:" + readbuffer);
		}
	}

	public static void main(String[] args) {
		String inputFilePath = "E:/kana_edited.txt";
		String outputFilePath = "E:/output.sql";
		new TextToSql_kana().doTextToSql(inputFilePath, outputFilePath);
	}

}
