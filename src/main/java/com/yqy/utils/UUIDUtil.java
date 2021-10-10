package com.yqy.utils;
import java.util.UUID;
/**
 * @program: seckill
 * @description: UUID工具类
 * @author: yqy
 * @create: 2021-10-09 17:44
 **/
public class UUIDUtil {
    public static String uuid() {
   return UUID.randomUUID().toString().replace("-", "");
  }
}
