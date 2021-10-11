package com.yqy.seckill.service.impl;

import com.yqy.seckill.pojo.TSeckillGoods;
import com.yqy.seckill.mapper.TSeckillGoodsMapper;
import com.yqy.seckill.service.ITSeckillGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yqy
 * @since 2021-10-10
 */
@Service
public class TSeckillGoodsServiceImpl extends ServiceImpl<TSeckillGoodsMapper, TSeckillGoods> implements ITSeckillGoodsService {
    @Autowired
    private TSeckillGoodsMapper seckillGoodsMapper;
    boolean updateById1(TSeckillGoods seckillGoods) {
        int i=seckillGoodsMapper.updateById1(seckillGoods);
        if (i>0){
            return true;
        }
        return false;
    }
}
