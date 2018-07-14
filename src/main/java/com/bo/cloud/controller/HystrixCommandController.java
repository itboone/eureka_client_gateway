package com.bo.cloud.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 断路由回调
 * @Author Bo
 * @Version 2018年7月11日　下午4:25:03
 * @码云 https://gitee.com/LeisureBo
 */
@RestController
public class HystrixCommandController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @RequestMapping("/hystrixFallback")
    public Object hystrixTimeout() {
    	logger.error("default hystrix via execute ...");
    	Map<String, String> retMap = new HashMap<>();
    	retMap.put("error", "service is unavailable");
        return retMap;
    }

}
