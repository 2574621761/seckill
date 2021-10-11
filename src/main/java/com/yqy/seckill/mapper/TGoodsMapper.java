package com.yqy.seckill.mapper;

import com.yqy.seckill.pojo.TGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqy.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yqy
 * @since 2021-10-10
 */
@Mapper
public interface TGoodsMapper extends BaseMapper<TGoods> {
    List<GoodsVo> findGoodVo();
    GoodsVo findById(Long goodsId);
}
