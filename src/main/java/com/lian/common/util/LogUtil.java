package com.lian.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * slf4j，对接所有的Log
 * 单例的Logger
 */
public class LogUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger("log");
    public static Logger getLogger(){
        return LOGGER;
    }
    public static Logger getLogger(Class<?>  z){
        return LoggerFactory.getLogger(z);
    }
    public static void info(String s,Object ...o){
        LOGGER.info(s,o);
    }
}
