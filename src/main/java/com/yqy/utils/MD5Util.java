package com.yqy.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;


@Component
public class MD5Util {
    private static final String salt="1a2b3c4d";
    public static String md5(String src){
        return DigestUtils.md2Hex(src);
    }
    /** 
    * @Description:
    * @Param:  
    * @return:  
    * @Author: yqy
    * @Date: 2021/10/9 
    */
    public static String inputPassToFromPass(String inputPass){
        String str=""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }
    public static String farmPassToDBPass(String pormPass,String salt){
        String str=""+salt.charAt(0)+salt.charAt(2)+pormPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }
    public static String inputPassToDBpass(String inputPass,String salt){
        String formPass=inputPassToFromPass(inputPass);
        String dbpass=farmPassToDBPass(formPass,salt);
        return dbpass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFromPass("123456"));
        System.out.println(farmPassToDBPass("8694dd505f2f2ee9f32a7262eec95b1d","1a2b3c4d"));
        System.out.println(inputPassToDBpass("123456","1a2b3c4d"));
    }
}
