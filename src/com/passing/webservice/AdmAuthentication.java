package com.passing.webservice;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.passing.util.LogUtil;
import com.passing.webservice.bean.AdmAccessToken;

public class AdmAuthentication
{
    public static String DatamarketAccessUri = "http://api.microsofttranslator.com/V2/Ajax.svc/Translate";
    private String clientId;
    private String clientSecret;
    private String request;

    public AdmAuthentication(String clientId, String clientSecret)
    {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        //If clientid or client secret has special characters, encode before sending request
        try {
			this.request = String.format(
					"grant_type=client_credentials&client_id=" + clientId
							+ "&client_secret=" + clientSecret
							+ "&scope=http://api.microsofttranslator.com",
					URLEncoder.encode(clientId, "utf-8"),
					URLEncoder.encode(clientSecret, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			LogUtil.log.info(e);
			e.printStackTrace();
		}
    }

    public AdmAccessToken GetAccessToken()
    {
        try {
			return HttpPost(DatamarketAccessUri, this.request);
		} catch (Exception e) {
			LogUtil.log.info(e);
			e.printStackTrace();
			return null;
		}
    }

    private AdmAccessToken HttpPost(String DatamarketAccessUri, String requestDetails) throws Exception
    {
//        //Prepare OAuth request 
//        WebRequest webRequest = WebRequest.Create(DatamarketAccessUri);
//        webRequest.ContentType = "application/x-www-form-urlencoded";
//        webRequest.Method = "POST";
//        byte[] bytes = Encoding.ASCII.GetBytes(requestDetails);
//        webRequest.ContentLength = bytes.Length;
//        using (Stream outputStream = webRequest.GetRequestStream())
//        {
//            outputStream.Write(bytes, 0, bytes.Length);
//        }
//        using (WebResponse webResponse = webRequest.GetResponse())
//        {
//            DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(AdmAccessToken));
//            //Get deserialized object from JSON stream
//            AdmAccessToken token = (AdmAccessToken)serializer.ReadObject(webResponse.GetResponseStream());
//            return token;
//        }
    	
//    	String urlParameters = DatamarketAccessUri;
    	URL url = new URL(DatamarketAccessUri);
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	connection.setDoOutput(true);
    	connection.setDoInput(true);
    	connection.setInstanceFollowRedirects(false);
    	connection.setRequestMethod("POST"); 
    	connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
    	connection.setRequestProperty("charset", "utf-8");
    	connection.setRequestProperty("Content-Length", "" + Integer.toString(requestDetails.getBytes().length));
    	// add
//    	connection.setRequestProperty("client_id", URLEncoder.encode(this.clientId, "utf-8"));
//    	connection.setRequestProperty("client_secret", URLEncoder.encode(this.clientSecret, "utf-8"));
//    	connection.setRequestProperty("grant_type", "client_credentials");
//    	connection.setRequestProperty("scope", "http://api.microsofttranslator.com");
    	connection.setUseCaches (false);
    	
//    	System.out.println("client_secret=" + connection.getRequestProperty("client_secret"));
//    	System.out.println("RequestMethod=" + connection.getRequestMethod());
//    	System.out.println("ResponseMessage=" + connection.getResponseMessage());
//    	System.out.println("ContentType=" + connection.getContentType());
//    	System.out.println("URL=" + connection.getURL());
    	
    	DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
    	wr.writeBytes(requestDetails);
    	System.out.println("ResponseMessage=" + connection.getResponseMessage());
    	System.out.println("RequestMethod=" + connection.getRequestMethod());
    	System.out.println("URL=" + connection.getURL());
    	System.out.println("client_secret=" + connection.getRequestProperty("client_secret"));
    	System.out.println("ContentType=" + connection.getContentType());
    	wr.flush();
    	wr.close();
    	connection.disconnect();
    	
//    	WebClient webClient = new WebClient();
//    	WebRequest request = new WebRequest(
//                new URL(DatamarketAccessUri), 
//                HttpMethod.POST);
//        request.setCharset("UTF-8");
//        
//        HtmlPage page = webClient.getPage(request);
//        WebResponse webResponse = page.getWebResponse();
//        InputStream js = webResponse.getContentAsStream();
        JSONObject jsonObj = JSONObject.fromObject(wr);
        
//        String access_token = jsonObj.getString("access_token");
//        String token_type = jsonObj.getString("token_type");
//        String expires_in = jsonObj.getString("expires_in");
//        String scope = jsonObj.getString("scope");
        
        String access_token = "access_token";
        String token_type = "token_type";
        String expires_in = "expires_in";
        String scope = "scope";
        
        AdmAccessToken token = new AdmAccessToken(access_token, token_type, expires_in, scope);
        return token;
    }
}
