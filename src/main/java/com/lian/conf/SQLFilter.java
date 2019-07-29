package com.lian.conf;

import com.lian.common.response.MyException;
/**
 * SQL过滤
 * @author chenshun
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str){
        if(str == null || str.length() <= 0){
            return null;
        }
        //去掉'|"|;|\字符
        str = str.replace( "'", "");
        str = str.replace( "\"", "");
        str = str.replace( ";", "");
        str = str.replace("\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.contains(keyword)){
                throw MyException.notAllowed(null,"包含非法字符");
            }
        }

        return str;
    }
}
