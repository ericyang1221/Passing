package com.passing.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.passing.hibernate.beans.TbEnFrequency;
import com.passing.spring.service.impl.UpdFrequencyTbServiceBean;
import com.passing.util.LogUtil;

public class UpdFrequencyTbAction extends BaseAction {

	private UpdFrequencyTbServiceBean updFrequencyTbServiceBean;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int result = 0;
		String wordId = request.getParameter("wordId");
		String word = request.getParameter("word");
		
		List<TbEnFrequency> searchRst = updFrequencyTbServiceBean.searchWord(word);
		TbEnFrequency wordInFrequency = new TbEnFrequency();
		wordInFrequency.setWord_id(Integer.parseInt(wordId));
		wordInFrequency.setWord(word);
		// insert a new record if there is a record
		if (searchRst.isEmpty()) {
			wordInFrequency.setFrequency(1);
			boolean insertRst = updFrequencyTbServiceBean.insertWord(wordInFrequency);
			if (insertRst) {
				result = 1;
				LogUtil.log.info("Insertion of TbEnFrequency is succeed.");
			} else {
				LogUtil.log.info("error occured during Insertion of TbEnFrequency");
			}
		// update the frequency if it is already exist
		} else {
			wordInFrequency.setFrequency(searchRst.get(0).getFrequency() + 1);
			boolean updRst = updFrequencyTbServiceBean.updateWordFrequency(wordInFrequency);
			if (updRst) {
				result = 1;
				LogUtil.log.info("Update of TbEnFrequency is succeed.");
			} else {
				LogUtil.log.info("error occured during Update of TbEnFrequency");
			}
		}

		super.makeJSONObject(response, "updResult", result);

		return mapping.findForward(null);
		
	}

	public void setUpdFrequencyTbServiceBean(
			UpdFrequencyTbServiceBean updFrequencyTbServiceBean) {
		this.updFrequencyTbServiceBean = updFrequencyTbServiceBean;
	}

}
