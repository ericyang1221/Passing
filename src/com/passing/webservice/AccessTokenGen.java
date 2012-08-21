package com.passing.webservice;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.passing.webservice.bean.AdmAccessToken;

public class AccessTokenGen {

	public AdmAccessToken GetAccessToken() {
		AdmAccessToken admToken;
	    String headerValue;
	    //Get Client Id and Client Secret from https://datamarket.azure.com/developer/applications/
	    String clientid = "mstranslatetest";
	    String clientsecret = "e2f3MjBcCS7EUb+bv3AkJiLdfdR8s/4eK0t0oTsF7HE=";
	    AdmAuthentication admAuth = new AdmAuthentication(clientid, clientsecret);
	    admToken = admAuth.GetAccessToken();
	    // Create a header with the access_token property of the returned token
//	    try {
//			headerValue = "Bearer" + " " + URLEncoder.encode(admToken.access_token, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
	    return admToken;
	}
}
