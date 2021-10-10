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
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long goodsId;

    private Long deliveryAddrId;

    private String goodsName;

    private Integer goodsCount;

    private BigDecimal goodsPrice;

    private Integer orderChanel;

    private Integer status;

    private Date createDate;

    private Date payDate;


}
