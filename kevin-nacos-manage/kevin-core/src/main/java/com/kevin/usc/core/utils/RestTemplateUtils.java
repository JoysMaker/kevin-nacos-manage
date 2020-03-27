package com.kevin.usc.core.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.kevin.usc.core.bean.WResponse;

@Component
public class RestTemplateUtils {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseEntity<WResponse> postForForm(String url, Map<String, String> paramMap){
		
    HttpHeaders headers = new HttpHeaders();
	//  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
	MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
	//  也支持中文
	paramMap.forEach((k,v)-> params.add(k, v));
	
	HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
	//  执行HTTP请求
	ResponseEntity<WResponse> response = restTemplate.postForEntity(url, requestEntity, WResponse.class);
    
	return  response;
	}
	
	public WResponse postForJson(String url,Map<String, String> paramMap){
	
		 HttpHeaders headers = new HttpHeaders();
		 MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		 headers.setContentType(type);
		 headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		 JSONObject jsonObj = new JSONObject();
		 paramMap.forEach((k,v)-> jsonObj.put(k, v));
		 HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
		 return restTemplate.postForEntity(url,formEntity,WResponse.class).getBody();
		
	}
 

}
