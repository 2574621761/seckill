package com.yqy.seckill.service;

import com.yqy.seckill.pojo.TGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqy.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yqy
 * @since 2021-10-10
 */
public interface ITGoodsService extends IService<TGoods> {

    List<GoodsVo> findGoodVo();

    TGoods findByid(Long goodsId);
}
