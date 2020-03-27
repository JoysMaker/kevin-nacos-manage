package com.kevin.usc.core.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.kevin.usc.core.exception.BusinessException;


public class WebUtils {
	
	//json入参非空校验
	public static void JsonParamMapCheck(Map<String, String> paramMap,String ...keys) {
		
		List<String> keyList = Arrays.asList(keys);
		
		keyList.forEach(key->{if(StringUtils.isBlank(paramMap.get(key))){throw new BusinessException(400,key+"为必填参数");}});
		
	}

	
}
