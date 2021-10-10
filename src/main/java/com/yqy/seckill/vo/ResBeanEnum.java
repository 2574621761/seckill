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
    MOBILE_ERROR(500211,"手机号码格式错误,请查正");
    private final Integer code;
    private final String message;
}
