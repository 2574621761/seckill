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
import com.yqy.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Autowired
    private RedisTemplate redisTemplate;
    /**
    * @Description: 秒杀功能
    * @Param:
    * @return:
    * @Author: yqy
    * @Date: 2021/10/10
    */
    @RequestMapping(value = "/doSeckill1")
    public String doSeckill1(Model model, TUser user,@PathVariable Long goodsId){
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
    @RequestMapping(value = "/doSeckill",method = RequestMethod.POST)
    @ResponseBody
    public RespBean doSeckill(Model model, TUser user,Long goodsId){
        System.out.println("秒杀功能");
        if(user==null){
            return RespBean.orror(ResBeanEnum.SESSION_ERROR);
        }
        model.addAttribute("user",user);
        GoodsVo goodsVo= goodsService.findByid(goodsId);
        System.out.println(goodsVo.getStockCount());
        //判断库存
        if(goodsVo.getStockCount()<1){
            return RespBean.orror(ResBeanEnum.EMPTY_STOCK);
        }
        //判断是否重复购买
//        TSeckillOrder seckillOrder=seckillOrderService.getOne(new QueryWrapper<TSeckillOrder>()
//                .eq("user_id",user.getId()).eq("goods_id",goodsId));
        TSeckillOrder seckillOrder= (TSeckillOrder) redisTemplate.opsForValue().get("order:"+user.getId()+":"+goodsId);
        if(seckillOrder!=null){
            return RespBean.orror(ResBeanEnum.REPEATE_ERROR);
        }
        System.out.println(goodsVo);
        TOrder order=orderService.seckill(user,goodsVo);
        System.out.println(order);
        return RespBean.success(order);
    }
}
