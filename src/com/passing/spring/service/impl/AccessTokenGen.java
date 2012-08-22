package com.passing.spring.service.impl;

import com.passing.consts.AccessTokenGenConsts;
import com.passing.struts.vo.AdmAccessTokenVo;

public class AccessTokenGen {

	public AdmAccessTokenVo GetAccessToken() {
	    String clientid = AccessTokenGenConsts.CLIENT_ID;
	    String clientsecret = AccessTokenGenConsts.CLIENT_SECRET;
	    
	    AdmAuthentication admAuth = new AdmAuthentication(clientid, clientsecret);
	    AdmAccessTokenVo admToken = admAuth.GetAccessToken();

	    return admToken;
	}
}
