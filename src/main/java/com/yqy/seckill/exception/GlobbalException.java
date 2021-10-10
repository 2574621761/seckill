package com.yqy.seckill.exception;

import com.yqy.seckill.vo.ResBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: seckill
 * @description: 全局异常
 * @author: yqy
 * @create: 2021-10-09 15:55
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobbalException extends RuntimeException{
    private ResBeanEnum resBeanEnum;
}
