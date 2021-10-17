package com.yqy.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.yqy.seckill.exception.GlobbalException;
import com.yqy.seckill.pojo.TOrder;
import com.yqy.seckill.mapper.TOrderMapper;
import com.yqy.seckill.pojo.TSeckillGoods;
import com.yqy.seckill.pojo.TSeckillOrder;
import com.yqy.seckill.pojo.TUser;
import com.yqy.seckill.service.ITOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqy.seckill.vo.GoodsVo;
import com.yqy.seckill.vo.OrderDetailVo;
import com.yqy.seckill.vo.ResBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private TGoodsServiceImpl goodsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    @Transactional
    public  TOrder seckill(TUser user, GoodsVo goodsVo) {
        TSeckillGoods seckillGoods=seckillGoodsService.getOne(new QueryWrapper<TSeckillGoods>().eq("goods_id",goodsVo.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount()-1);
        boolean seckillGoodsResult=seckillGoodsService.update(new UpdateWrapper<TSeckillGoods>().setSql("stock_count=stock_count-1").
                eq("goods_id",goodsVo.getId()).gt("stock_count",0));
        //解决库存超卖
        if(!seckillGoodsResult){
            return null;
        }
        //seckillGoodsService.updateById1(seckillGoods);
        //生成订单
        TOrder order=new TOrder();
        order.setUserId(user.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsId(goodsVo.getId());
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
        redisTemplate.opsForValue().set("order:"+user.getId()+":"+goodsVo.getId(),seckillOrder);
        return order;
    }

    @Override
    public OrderDetailVo detail(Long userId,Long orderId) {
        if(orderId==null){
            throw new GlobbalException(ResBeanEnum.ORDER_NOT_EXIST);
        }
        TOrder order=orderMapper.detail(userId,orderId);
         GoodsVo goodsVo=goodsService.findByid(order.getGoodsId());
         OrderDetailVo orderDetailVo=new OrderDetailVo();
         orderDetailVo.setGoodsVo(goodsVo);
         orderDetailVo.setTorder(order);
         return orderDetailVo;
    }
}
