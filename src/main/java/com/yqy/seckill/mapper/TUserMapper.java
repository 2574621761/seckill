package com.yqy.seckill.mapper;

import com.yqy.seckill.pojo.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yqy
 * @since 2021-10-07
 */
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {
@Select("SELECT id, nickname,password,slat,head,register_date,last_login_date,login_count FROM t_user WHERE id=#{moblie}")
    TUser login(String moblie);
}
