package com.yqy.seckill.mapper;

import com.yqy.seckill.pojo.TOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqy.seckill.vo.OrderDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
public interface TOrderMapper extends BaseMapper<TOrder> {
    @Update("update t_order set status=#{status} where user_id=#{userId} and goodsid=#{goodsId}")
    void updateStatus(Long goodsId, Long userId,Integer status);
    @Select("select * from t_order where id=#{orderId} and user_id=#{userId}")
    TOrder detail(Long userId, Long orderId);
}
