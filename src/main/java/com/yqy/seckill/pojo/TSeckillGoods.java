package com.yqy.seckill.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yqy
 * @since 2021-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TSeckillGoods implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
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
