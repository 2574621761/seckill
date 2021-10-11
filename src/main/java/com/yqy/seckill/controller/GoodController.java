package com.yqy.seckill.controller;

import com.yqy.seckill.pojo.TUser;
import com.yqy.seckill.service.impl.TGoodsServiceImpl;
import com.yqy.seckill.service.impl.TUserServiceImpl;
import com.yqy.seckill.vo.GoodsVo;
import com.yqy.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @program: seckill
 * @description: 商品控制层
 * @author: yqy
 * @create: 2021-10-09 17:59
 **/
@Controller
@RequestMapping(value = "goods")
public class GoodController {
    @Autowired
    private TUserServiceImpl userService;
    @Autowired
    private TGoodsServiceImpl goodsService;
    /**
    * @Description: 用户登录成功后跳转到商品页
    * @Param:
    * @return:
    * @Author: yqy
    * @Date: 2021/10/9
    */
    @RequestMapping(value = "tolist")
    public String tolist(Model model,TUser user){
//        if(StringUtils.isEmpty(ticket)){
//            return "login";
//        }
//        //TUser user= (TUser) session.getAttribute(ticket);
//        TUser user=userService.getUserByCookie(ticket,request,response);
//        if(user==null){
//            return "login";
//        }
        model.addAttribute("goodsList",goodsService.findGoodVo());
        model.addAttribute("user",user);
        return "goodsList";
    }
    /**
    * @Description: 跳转商品详情页
    * @Param: 商品id
    * @return:
    * @Author: yqy
    * @Date: 2021/10/10
    */
    @RequestMapping(value = "/toDetail/{goodsId}")
    public String toDetail(@PathVariable Long goodsId,Model model,TUser user){
        model.addAttribute("user",user);
        GoodsVo goodsVo=goodsService.findByid(goodsId);
        Date startDate=goodsVo.getStartDate();
        Date endDate=goodsVo.getEndDate();
        Date newDate=new Date();
        int secKillStatus=0;
        int remainSeconds=0;
        if(newDate.before(startDate)){
            remainSeconds= (int) ((startDate.getTime()-newDate.getTime())/1000);
        }else if(newDate.after(endDate)){
            secKillStatus=2;
            remainSeconds=-1;
        }else {
            secKillStatus=1;
            remainSeconds=0;
        }
        model.addAttribute("remainSeconds",remainSeconds);
        model.addAttribute("secKillStatus",secKillStatus);
        model.addAttribute("goods",goodsVo);
        return "goodsDetail";
    }
}
