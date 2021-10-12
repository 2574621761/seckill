package com.yqy.seckill.vo;

import com.yqy.seckill.pojo.TUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: seckill
 * @description: 详情返回对象
 * @author: yqy
 * @create: 2021-10-12 17:56
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateVo {
    private TUser tuser;
    private GoodsVo goodsVo;
    private int secKillStatus;
    private int remainSeconds;
}
