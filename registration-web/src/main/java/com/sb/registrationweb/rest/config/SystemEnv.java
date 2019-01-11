package com.sb.registrationweb.rest.config;

public class SystemEnv {

    public static String getServerAddress(final String envName, final String defaultValue){
        String envVal = System.getenv(envName);
        if(envVal == null || "".equals(envVal)){
            return defaultValue;
        }
        return envVal;
    }
}
