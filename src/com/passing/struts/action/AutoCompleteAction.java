package com.passing.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.passing.hibernate.beans.TbEnFrequency;
import com.passing.spring.service.impl.AutoCompleteServiceBean;

public class AutoCompleteAction extends BaseAction {

	private AutoCompleteServiceBean autoCompleteServiceBean;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String searchStr = request.getParameter("searchStr");

		List<String> searchRst = autoCompleteServiceBean.searchForAutoComplete(searchStr);
		// test code
//		if (searchStr.equals("t")) {
//			searchRst.add("test");
//			searchRst.add("teacher");
//			searchRst.add("teach");
//			searchRst.add("tree");
//		} else if (searchStr.equals("te")){
//			searchRst.add("test");
//			searchRst.add("teacher");
//			searchRst.add("teach");
//		} else if (searchStr.equals("tea")) {
//			searchRst.add("teacher");
//			searchRst.add("teach");
//		}
		

		super.makeJSONObject(response, "matchedWords", searchRst);

		return mapping.findForward(null);
		
	}

	public AutoCompleteServiceBean getAutoCompleteServiceBean() {
		return autoCompleteServiceBean;
	}

	public void setAutoCompleteServiceBean(
			AutoCompleteServiceBean autoCompleteServiceBean) {
		this.autoCompleteServiceBean = autoCompleteServiceBean;
	}

}
