package com.yqy.seckill.service.impl;

import com.yqy.seckill.pojo.TGoods;
import com.yqy.seckill.mapper.TGoodsMapper;
import com.yqy.seckill.service.ITGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqy.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yqy
 * @since 2021-10-10
 */
@Service
public class TGoodsServiceImpl extends ServiceImpl<TGoodsMapper, TGoods> implements ITGoodsService {
    @Autowired
    private TGoodsMapper goodsMapper;
    @Override
    public List<GoodsVo> findGoodVo() {
        return goodsMapper.findGoodVo();
    }

    @Override
    public GoodsVo findByid(Long goodsId) {
        return goodsMapper.findById(goodsId);
    }

}
