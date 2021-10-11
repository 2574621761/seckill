package com.yqy.seckill.service;

import com.yqy.seckill.pojo.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqy.seckill.pojo.TUser;
import com.yqy.seckill.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yqy
 * @since 2021-10-10
 */
public interface ITOrderService extends IService<TOrder> {
    TOrder seckill(TUser user, GoodsVo goodsVo);
}
