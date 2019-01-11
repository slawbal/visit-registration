package com.sb.registrationweb.rest.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RestConfig {

	private static final String DEFAULT_SERVER_URL = "localhost:8888";
	private static final String SERVER_ADDR_PATTERN = "http://%s/scheduler-tool/";
	private static final String SERVER_ADDR = "VR_SERVER_ADDR";
	private static final String SERVER_URL = getBaseUri();
	
	public static String getBaseUri(){
		String addr = SystemEnv.getServerAddress(SERVER_ADDR, DEFAULT_SERVER_URL);
		return String.format(SERVER_ADDR_PATTERN, addr);
	}
	
	public String createURL(String path){
		return SERVER_URL + path;
	}




}
