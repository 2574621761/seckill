package com.yqy.seckill.exception;

import com.yqy.seckill.vo.ResBeanEnum;
import com.yqy.seckill.vo.RespBean;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



/**
 * @program: seckill
 * @description: 全局异常处理类
 * @author: yqy
 * @create: 2021-10-09 15:57
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public RespBean EExceptionHandler(Exception e){
        if(e instanceof GlobbalException){
            GlobbalException exception= (GlobbalException) e;
            return RespBean.orror(exception.getResBeanEnum());
        }else if(e instanceof BindException){
            BindException bindException= (BindException) e;
            RespBean respBean= RespBean.orror(ResBeanEnum.BIND_ERROR);
            respBean.setMessage("参数校验异常"+bindException.getAllErrors().get(0).getDefaultMessage());
            return respBean;
        }
        return RespBean.orror(ResBeanEnum.ERROR);
    }
}
