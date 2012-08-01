package com.passing.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.passing.consts.SearchEnToCnConsts;
import com.passing.hibernate.beans.TbEnWord;
import com.passing.hibernate.dao.TbEnWordDao;
import com.passing.spring.service.impl.SearchEnToCnBean;
import com.passing.struts.form.SearchEnToCnForm;

public class SearchEnToCnAction extends BaseAction {

	// 使用普遍依赖注入方式
	private SearchEnToCnBean searchEnToCnBean;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String searchStr = request.getParameter("searchStr");

		List<Object[]> enWordInfo = searchEnToCnBean.searchEnToCn(searchStr);

		if (enWordInfo.isEmpty()) {
			
			this.makeJSONObject(response, "enWordResponse", SearchEnToCnConsts.ENTOCN_RSLT_NONE);
		} else {
			// json process for EnWord
			List<EnWord> jsonEnWordLs = new ArrayList<EnWord>();
			EnWord jsonEnWord;
			for (int i = 0; i < enWordInfo.size(); i ++) {
				Object[] enWord = enWordInfo.get(i);
				jsonEnWord = new EnWord(enWord[0], enWord[1], enWord[2],
						enWord[3], enWord[4], enWord[5], enWord[6], enWord[7],
						enWord[8], enWord[9], enWord[10]);
				jsonEnWordLs.add(jsonEnWord);
			}
			
			// json process for EnExtdWord
			List<Object[]> enExtdWordInfo = searchEnToCnBean.searchEnToCnExtdInfo(searchStr);
			List<EnExtdWord> jsonEnExtdWordLs = new ArrayList<EnExtdWord>();
			if (enExtdWordInfo.isEmpty()) {
				jsonEnExtdWordLs = null;
			} else {
				EnExtdWord jsonEnExtdWord;
				for (int i = 0; i < enExtdWordInfo.size(); i ++) {
					Object[] enExtdWord = enExtdWordInfo.get(i);
					jsonEnExtdWord = new EnExtdWord(enExtdWord[0], enExtdWord[1], enExtdWord[2],
							enExtdWord[3], enExtdWord[4], enExtdWord[5], enExtdWord[6], enExtdWord[7],
							enExtdWord[8], enExtdWord[9], enExtdWord[10], enExtdWord[11]);
					jsonEnExtdWordLs.add(jsonEnExtdWord);
				}
			}
			
			Map<String, Object> jsonResponseLs = new HashMap<String, Object>();
			jsonResponseLs.put("enWordInfo", jsonEnWordLs);
			jsonResponseLs.put("enExtdWordInfo", jsonEnExtdWordLs);
			
			this.makeJSONObject(response, "enWordResponse", jsonResponseLs);
		}
		
		return (mapping.findForward(null));
	}

	public void setSearchEnToCnBean(SearchEnToCnBean searchEnToCnBean) {
		this.searchEnToCnBean = searchEnToCnBean;
	}

	public SearchEnToCnBean getSearchEnToCnBean() {
		return searchEnToCnBean;
	}
	
	public class EnWord {
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
		
//		public EnWord() {
//			
//		}
		
		public EnWord(Object word, Object extdAttr, Object mean, Object dictId,
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
	
	public class EnExtdWord {
		private Object extdWord;
		private Object extdWordExtdAttr;
		private Object extdWordMean;
		private Object dictId;
		private Object wordId;
		private Object extdWordId;
		private Object extdWordPtsp;
		private Object extdWordMeanNum;
		private Object extdWordExmpNum;
		private Object extdWordExmpExtdAttr;
		private Object extdWordExmp;
		private Object extdWordExmpMean;
		
		public EnExtdWord(Object extdWord, Object extdWordExtdAttr,
				Object extdWordMean, Object dictId, Object wordId,
				Object extdWordId, Object extdWordPtsp, Object extdWordMeanNum,
				Object extdWordExmpNum, Object extdWordExmpExtdAttr,
				Object extdWordExmp, Object extdWordExmpMean) {
			super();
			this.extdWord = extdWord;
			this.extdWordExtdAttr = extdWordExtdAttr;
			this.extdWordMean = extdWordMean;
			this.dictId = dictId;
			this.wordId = wordId;
			this.extdWordId = extdWordId;
			this.extdWordPtsp = extdWordPtsp;
			this.extdWordMeanNum = extdWordMeanNum;
			this.extdWordExmpNum = extdWordExmpNum;
			this.extdWordExmpExtdAttr = extdWordExmpExtdAttr;
			this.extdWordExmp = extdWordExmp;
			this.extdWordExmpMean = extdWordExmpMean;
		}

		public Object getExtdWord() {
			return extdWord;
		}

		public void setExtdWord(Object extdWord) {
			this.extdWord = extdWord;
		}

		public Object getExtdWordExtdAttr() {
			return extdWordExtdAttr;
		}

		public void setExtdWordExtdAttr(Object extdWordExtdAttr) {
			this.extdWordExtdAttr = extdWordExtdAttr;
		}

		public Object getExtdWordMean() {
			return extdWordMean;
		}

		public void setExtdWordMean(Object extdWordMean) {
			this.extdWordMean = extdWordMean;
		}

		public Object getDictId() {
			return dictId;
		}

		public void setDictId(Object dictId) {
			this.dictId = dictId;
		}

		public Object getWordId() {
			return wordId;
		}

		public void setWordId(Object wordId) {
			this.wordId = wordId;
		}

		public Object getExtdWordId() {
			return extdWordId;
		}

		public void setExtdWordId(Object extdWordId) {
			this.extdWordId = extdWordId;
		}

		public Object getExtdWordPtsp() {
			return extdWordPtsp;
		}

		public void setExtdWordPtsp(Object extdWordPtsp) {
			this.extdWordPtsp = extdWordPtsp;
		}

		public Object getExtdWordMeanNum() {
			return extdWordMeanNum;
		}

		public void setExtdWordMeanNum(Object extdWordMeanNum) {
			this.extdWordMeanNum = extdWordMeanNum;
		}

		public Object getExtdWordExmpNum() {
			return extdWordExmpNum;
		}

		public void setExtdWordExmpNum(Object extdWordExmpNum) {
			this.extdWordExmpNum = extdWordExmpNum;
		}

		public Object getExtdWordExmpExtdAttr() {
			return extdWordExmpExtdAttr;
		}

		public void setExtdWordExmpExtdAttr(Object extdWordExmpExtdAttr) {
			this.extdWordExmpExtdAttr = extdWordExmpExtdAttr;
		}

		public Object getExtdWordExmp() {
			return extdWordExmp;
		}

		public void setExtdWordExmp(Object extdWordExmp) {
			this.extdWordExmp = extdWordExmp;
		}

		public Object getExtdWordExmpMean() {
			return extdWordExmpMean;
		}

		public void setExtdWordExmpMean(Object extdWordExmpMean) {
			this.extdWordExmpMean = extdWordExmpMean;
		}
		
	}

}
