package com.passing.webservice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.passing.struts.action.BaseAction;
import com.passing.webservice.AccessTokenGen;
import com.passing.webservice.bean.AdmAccessToken;

public class AccessTokenGenAction extends BaseAction{

	private AccessTokenGen accessTokenGen;
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdmAccessToken accessToken = accessTokenGen.GetAccessToken();
		this.makeJSONObject(response, "response", accessToken);
		return (mapping.findForward(null));
	}
	public AccessTokenGen getAccessTokenGen() {
		return accessTokenGen;
	}
	public void setAccessTokenGen(AccessTokenGen accessTokenGen) {
		this.accessTokenGen = accessTokenGen;
	}
}
