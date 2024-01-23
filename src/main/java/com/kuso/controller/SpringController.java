package com.kuso.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.kuso.entity.Member;
import com.kuso.serviceImpl.KusoServiceImpl;
import com.kuso.utility.HttpUtil;
import com.kuso.utility.LogUtil;

@RestController
@RequestMapping("/SpringBootTest/Spring")
public class SpringController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LogUtil logUtil;
	@Autowired
	private HttpUtil httpUtil;
	@Autowired
	private KusoServiceImpl kusoServiceImpl;
	
	@RequestMapping(value = "/helloSpringBoot")
	public String helloSpringBoot(){
		return "Hello Spring Boot";
	}
	@RequestMapping(value = "/query")
	public ResponseEntity<Member> query(@Param(value = "account") String account){
		logger.info("query");
		
		logUtil.writeLog("http://localhost:8086/LogMessageWeb/Log/writeLog","SpringBootTest","SpringController的query","記Log","kuku");
		
		Member member = kusoServiceImpl.query(account);
		
		if(member != null){
			logger.info("account={}",member.getAccount());
			logger.info("username={}",member.getUsername());
		}
		
		return ResponseEntity.ok().body(member);
	}
	@RequestMapping(value = "/createToken")
	public String createToken(@RequestParam Map<String,String> claimMap){
		logger.info("createToken");
		logger.info("claimMap={}",claimMap);
		
		return new Gson().toJson(httpUtil.httpPost("http://localhost:8088/JWTWeb/Token/createToken",claimMap));
	}
	@RequestMapping(value = "/verifyToken")
	public String verifyToken(@RequestParam Map<String,String> requestParamMap){
		logger.info("verifyToken");
		logger.info("requestParamMap={}",requestParamMap);
		
		return new Gson().toJson(httpUtil.httpPost("http://localhost:8088/JWTWeb/Token/verifyToken",requestParamMap));
	}
}
