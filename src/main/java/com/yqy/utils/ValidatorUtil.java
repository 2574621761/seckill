package com.yqy.utils;

import org.thymeleaf.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
    private static final Pattern mobile_pattern=Pattern.compile("[1]([3-9])[0-9]{9}$");
    /**
    * @Description: 手机号校验
    * @Param: mobile
    * @return:  为真返回true,否则返回false
    * @Author: yqy
    * @Date: 2021/10/9
    */
    public static boolean ismobile(String mobile){
        if(StringUtils.isEmpty(mobile)){
            return false;
        }
        Matcher matcher= mobile_pattern.matcher(mobile);
        return matcher.matches();
    }
}
