package com.yqy.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object object;
/** 
* @Description: 成功返回结果 
* @Param:  
* @return:  
* @Author: yqy
* @Date: 2021/10/9 
*/
    public static RespBean success(){
        return new RespBean(ResBeanEnum.SUCCESS.getCode(),ResBeanEnum.SUCCESS.getMessage(), null);
    }
    public static RespBean success(Object object){
        return new RespBean(ResBeanEnum.SUCCESS.getCode(),ResBeanEnum.SUCCESS.getMessage(), object);
    }
    public static RespBean orror(ResBeanEnum resBeanEnum){
        return new RespBean(resBeanEnum.getCode(),resBeanEnum.getMessage(), null);
    }
    public static RespBean orror(ResBeanEnum resBeanEnum,Object object){
        return new RespBean(resBeanEnum.getCode(),resBeanEnum.getMessage(), object);
    }
}
