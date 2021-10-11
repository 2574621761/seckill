package com.yqy.seckill.vo;

import com.yqy.seckill.pojo.TGoods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: seckill
 * @description: 商品返回对象
 * @author: yqy
 * @create: 2021-10-10 14:21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo extends TGoods {
    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 秒杀价
     */
    private BigDecimal seckillPrice;

    /**
     * 库存数量
     */
    private Integer stockCount;

    /**
     * 秒杀开始时间
     */
    private Date startDate;

    /**
     * 秒杀结束时间
     */
    private Date endDate;

}
