package com.yqy.seckill.controller;


import com.yqy.seckill.pojo.TUser;
import com.yqy.seckill.service.impl.TOrderServiceImpl;
import com.yqy.seckill.vo.OrderDetailVo;
import com.yqy.seckill.vo.ResBeanEnum;
import com.yqy.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yqy
 * @since 2021-10-10
 */
@Controller
@RequestMapping(value = "/order")
public class TOrderController {
    @Autowired
    private TOrderServiceImpl orderService;
    @RequestMapping(value = "/detail")
    @ResponseBody
    public RespBean detail(TUser user,Long orderId){
        if(user==null){
            return RespBean.orror(ResBeanEnum.SESSION_ERROR);
        }
        OrderDetailVo orderDetailVo=orderService.detail(user.getId(),orderId);
        return RespBean.success(orderDetailVo);
    }
}
