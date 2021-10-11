package com.yqy.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqy.seckill.pojo.TOrder;
import com.yqy.seckill.mapper.TOrderMapper;
import com.yqy.seckill.pojo.TSeckillGoods;
import com.yqy.seckill.pojo.TSeckillOrder;
import com.yqy.seckill.pojo.TUser;
import com.yqy.seckill.service.ITOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqy.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yqy
 * @since 2021-10-10
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {
    @Autowired
    private TSeckillGoodsServiceImpl seckillGoodsService;
    @Autowired
    private TOrderMapper orderMapper;
    @Autowired
    private TSeckillOrderServiceImpl seckillOrderService;
    @Override
    @Transactional
    public TOrder seckill(TUser user, GoodsVo goodsVo) {
        TSeckillGoods seckillGoods=seckillGoodsService.getOne(new QueryWrapper<TSeckillGoods>().eq("goods_id",goodsVo.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount()-1);
        seckillGoodsService.updateById1(seckillGoods);
        //生成订单
        TOrder order=new TOrder();
        order.setUserId(user.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsId(goodsVo.getGoodsId());
        order.setGoodsCount(1);
        order.setGoodsName(goodsVo.getGoodsName());
        order.setGoodsPrice(seckillGoods.getSeckillPrice());
        order.setOrderChanel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
        //生成秒杀订单
        TSeckillOrder seckillOrder=new TSeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setGoodsId(seckillGoods.getGoodsId());
        seckillOrderService.save(seckillOrder);
        return order;
    }
}
