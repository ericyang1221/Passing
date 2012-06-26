package com.passing.struts.form;
import org.apache.struts.action.ActionForm;

public class SearchEnToCnForm extends ActionForm{
	private String searchStr;

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

}
