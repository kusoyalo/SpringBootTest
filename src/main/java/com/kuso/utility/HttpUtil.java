package com.kuso.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpUtil{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommonUtil commonUtil;
	
	public Map<String,String> httpPost(String uri,Map<String,String> requestParamMap){
		Map<String,String> returnMap = new HashMap<String,String>();
		returnMap.put("result","FALSE");
		
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		
		try{
			httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(uri);
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			for(String key : requestParamMap.keySet()){
				params.add(new BasicNameValuePair(key,requestParamMap.get(key)));
			}
			
		    httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
			
		    httpResponse = httpClient.execute(httpPost);
		    
		    int statusCode = httpResponse.getStatusLine().getStatusCode();
		    logger.info("statusCode={}",statusCode);
		    
		    if(statusCode != 200){
		    	returnMap.put("message","statusCode=" + statusCode);
		    	return returnMap;
		    }
		    
		    HttpEntity entity = httpResponse.getEntity();
		    String responseString = EntityUtils.toString(entity,"UTF-8");
		    logger.info("responseString={}",responseString);
		    
		    returnMap.put("result","TRUE");
		    returnMap.put("response",responseString);
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			
			returnMap.put("message",e.getMessage());
		}
		
		commonUtil.autoClose(httpResponse);
		commonUtil.autoClose(httpClient);
		
		return returnMap;
	}
}
