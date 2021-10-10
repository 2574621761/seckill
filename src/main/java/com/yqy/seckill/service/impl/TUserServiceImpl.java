package com.yqy.seckill.service.impl;

import com.yqy.seckill.exception.GlobbalException;
import com.yqy.seckill.pojo.TUser;
import com.yqy.seckill.mapper.TUserMapper;
import com.yqy.seckill.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqy.seckill.vo.LOginVo;
import com.yqy.seckill.vo.ResBeanEnum;
import com.yqy.seckill.vo.RespBean;
import com.yqy.utils.CookieUtil;
import com.yqy.utils.MD5Util;
import com.yqy.utils.UUIDUtil;
import com.yqy.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yqy
 * @since 2021-10-07
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {
    @Autowired
    private TUserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public RespBean login(LOginVo lOginVo, HttpServletRequest request, HttpServletResponse response) {

        String moblie = lOginVo.getMobile();
        String password = lOginVo.getPassword();
//        if (StringUtils.isEmpty(moblie) || StringUtils.isEmpty(password)) {
//            return RespBean.orror(ResBeanEnum.LOGIN_ERROR);
//        }
//        if(!ValidatorUtil.ismobile(moblie)){
//            return RespBean.orror(ResBeanEnum.MOBILE_ERROR);
//        }
        TUser user=userMapper.login(moblie);
        if(user==null){
            throw new GlobbalException(ResBeanEnum.LOGIN_ERROR);
        }
        /*校验密码是否正确
        将前端传来的密码进行加密之后与数据库查到的密码进行比较
         */
        if(!MD5Util.farmPassToDBPass(password,user.getSlat()).equals(user.getPassword())){
            throw new GlobbalException(ResBeanEnum.LOGIN_ERROR);
        }
        //生成cookie
        String ticket= UUIDUtil.uuid();
        redisTemplate.opsForValue().set("user:"+ticket,user);
       // request.getSession().setAttribute(ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return RespBean.success();
    }

    @Override
    public TUser getUserByCookie(String userTickte,HttpServletRequest request,HttpServletResponse response) {
        if(StringUtils.isEmpty(userTickte)){
            return null;
        }
        TUser user= (TUser) redisTemplate.opsForValue().get("user:"+userTickte);
        if (user!=null){
            CookieUtil.setCookie(request,response,"userTicket",userTickte);
        }
        return user;
    }
}
