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

		super.makeJSONObject(response, "source", searchRst);

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
