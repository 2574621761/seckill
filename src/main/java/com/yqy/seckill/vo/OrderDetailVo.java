package com.yqy.seckill.vo;

import com.yqy.seckill.pojo.TOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: seckill
 * @description: 订单详情返回对象
 * @author: yqy
 * @create: 2021-10-17 14:28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailVo {
    private GoodsVo goodsVo;
    private TOrder torder;
}
