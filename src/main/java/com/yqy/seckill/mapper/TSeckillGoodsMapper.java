package com.yqy.seckill.mapper;

import com.yqy.seckill.pojo.TSeckillGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yqy
 * @since 2021-10-10
 */
@Mapper
public interface TSeckillGoodsMapper extends BaseMapper<TSeckillGoods> {
    @Update("UPDATE t_seckill_goods SET goods_id=#{goodsId}, seckill_price=#{seckillPrice}, stock_count=#{stockCount}, start_date=#{startDate}, end_date=#{endDate} WHERE goods_id=#{goodsId}")
    Integer updateById1(TSeckillGoods seckillGoods);
}
