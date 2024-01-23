package com.kuso.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogUtil{
	@Autowired
	private HttpUtil httpUtil;
	
	public Map<String,String> writeLog(String uri,String systemName,String functionName,String logMessage,String createUser){
		Map<String,String> requestParamMap = new HashMap<String,String>();
		requestParamMap.put("systemName",systemName);
		requestParamMap.put("functionName",functionName);
		requestParamMap.put("logMessage",logMessage);
		requestParamMap.put("createUser",createUser);
		
		return httpUtil.httpPost(uri,requestParamMap);
	}
}
