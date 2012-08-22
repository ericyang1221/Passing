package com.passing.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

import com.passing.consts.AccessTokenGenConsts;
import com.passing.struts.vo.AdmAccessTokenVo;
import com.passing.util.LogUtil;

/**
 * request to Azure DataMarket to get access token
 * 
 * @author weishijie
 *
 */
public class AdmAuthentication
{
    private String clientId;
    private String clientSecret;

    public AdmAuthentication(String clientId, String clientSecret)
    {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public AdmAccessTokenVo GetAccessToken()
    {
        try {
			return HttpPost(AccessTokenGenConsts.DATA_MARKET_ACCESS_URI);
		} catch (Exception e) {
			LogUtil.log.info(e);
			e.printStackTrace();
			return null;
		}
    }

    /**
     * Make a http post request to the token service
     * 
     * @param DatamarketAccessUri https://datamarket.accesscontrol.windows.net/v2/OAuth2-13
     * @return AdmAccessTokenVo object
     * @throws Exception
     */
    private AdmAccessTokenVo HttpPost(String DatamarketAccessUri) throws Exception
    {
    	HttpClient httpClient = new DefaultHttpClient();
    	//建立HttpPost对象
    	HttpPost httppost = new HttpPost(DatamarketAccessUri);
    	//建立一个NameValuePair数组，用于存储欲传送的参数
    	List<NameValuePair> params=new ArrayList<NameValuePair>();
    	//添加参数
    	params.add(new BasicNameValuePair("client_id", this.clientId));
    	params.add(new BasicNameValuePair("client_secret", this.clientSecret));
    	params.add(new BasicNameValuePair("grant_type", AccessTokenGenConsts.GRANT_TYPE));
    	params.add(new BasicNameValuePair("scope", AccessTokenGenConsts.SCOPE));
    	//设置编码
    	httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
    	//发送Post,并返回一个HttpResponse对象
    	HttpResponse response = httpClient.execute(httppost);
    	
    	String access_token = "";
        String token_type = "";
        String expires_in = "";
        String scope = "";
        //如果状态码为200,就是正常返回
    	if(response.getStatusLine().getStatusCode() == 200){
    		//如果是下载文件,可以用response.getEntity().getContent()返回InputStream
			String result=EntityUtils.toString(response.getEntity());
			JSONObject jsonObj = JSONObject.fromObject(result);
			
			access_token = jsonObj.getString("access_token");
	        token_type = jsonObj.getString("token_type");
	        expires_in = jsonObj.getString("expires_in");
	        scope = jsonObj.getString("scope");
		}
        
        AdmAccessTokenVo token = new AdmAccessTokenVo(access_token, token_type, expires_in, scope);
        return token;
    }
}
