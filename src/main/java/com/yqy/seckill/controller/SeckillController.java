package com.yqy.seckill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqy.seckill.pojo.TOrder;
import com.yqy.seckill.pojo.TSeckillOrder;
import com.yqy.seckill.pojo.TUser;
import com.yqy.seckill.service.impl.TGoodsServiceImpl;
import com.yqy.seckill.service.impl.TOrderServiceImpl;
import com.yqy.seckill.service.impl.TSeckillOrderServiceImpl;
import com.yqy.seckill.vo.GoodsVo;
import com.yqy.seckill.vo.ResBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: seckill
 * @description: 秒杀页面
 * @author: yqy
 * @create: 2021-10-10 16:52
 **/
@Controller
@RequestMapping(value = "/seckill")
public class SeckillController {
    @Autowired
    private TGoodsServiceImpl goodsService;
    @Autowired
    private TSeckillOrderServiceImpl seckillOrderService;
    @Autowired
    private TOrderServiceImpl orderService;
    /**
    * @Description: 秒杀功能
    * @Param:
    * @return:
    * @Author: yqy
    * @Date: 2021/10/10
    */
    @RequestMapping(value = "/doSeckill")
    public String doSeckill(Model model, TUser user,Long goodsId){
        if(user==null){
            return "login";
        }
        model.addAttribute("user",user);
        GoodsVo goodsVo= goodsService.findByid(goodsId);
        if(goodsVo.getStockCount()<1){
            model.addAttribute("errmsg", ResBeanEnum.EMPTY_STOCK.getMessage());
            return "seckillFail";
        }
        TSeckillOrder seckillOrder=seckillOrderService.getOne(new QueryWrapper<TSeckillOrder>()
                .eq("user_id",user.getId()).eq("goods_id",goodsId));
        if(seckillOrder!=null){
            model.addAttribute("errmsg", ResBeanEnum.REPEATE_ERROR.getMessage());
            return "seckillFail";
        }
        System.out.println(goodsVo);
        TOrder order=orderService.seckill(user,goodsVo);
        model.addAttribute("order",order);
        model.addAttribute("goods",goodsVo);
        return "OrderDetail";
    }
}
