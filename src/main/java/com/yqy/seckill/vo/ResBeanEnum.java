package com.yqy.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ResBeanEnum {
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务器异常"),
    LOGIN_ERROR(500210,"用户名或密码错误"),
    BIND_ERROR(5002112,"参数校验异常"),
    MOBILE_ERROR(500211,"手机号码格式错误,请查正"),
    EMPTY_STOCK(500500,"库存不足"),
    REPEATE_ERROR(5005001,"该商品每人限购一件"),
    MOBILE_NOT_ERROR(5002113,"手机号码不存在"),
    UPDATE_PASSWORD_ERROR(5002114,"更新密码失败"),
    SESSION_ERROR(5000215,"用户未登录"),
    ORDER_NOT_EXIST(500311,"订单信息不存在");


    private final Integer code;
    private final String message;
}
