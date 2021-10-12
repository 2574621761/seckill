package com.yqy.seckill.controller;

import com.yqy.seckill.pojo.TUser;
import com.yqy.seckill.service.impl.TGoodsServiceImpl;
import com.yqy.seckill.service.impl.TUserServiceImpl;
import com.yqy.seckill.vo.DateVo;
import com.yqy.seckill.vo.GoodsVo;
import com.yqy.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: seckill
 * @description: 商品控制层
 * @author: yqy
 * @create: 2021-10-09 17:59
 **/
@Controller
@RequestMapping(value = "/goods")
public class GoodController {
    @Autowired
    private TUserServiceImpl userService;
    @Autowired
    private TGoodsServiceImpl goodsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;
    /**
    * @Description: 用户登录成功后跳转到商品页,使用redis缓存页面，优化吞吐量
    * @Param:
    * @return:
    * @Author: yqy
    * @Date: 2021/10/9
    */
    @RequestMapping(value = "/tolist",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String tolist(Model model,TUser user,HttpServletResponse response,HttpServletRequest request){
//        if(StringUtils.isEmpty(ticket)){
//            return "login";
//        }
//        //TUser user= (TUser) session.getAttribute(ticket);
//        TUser user=userService.getUserByCookie(ticket,request,response);
//        if(user==null){
//            return "login";
//        }
        //Redis中获取页面
        ValueOperations valueOperations=redisTemplate.opsForValue();
        String html=(String)valueOperations.get("goodsList");
        if(!StringUtils.isEmpty(html)){
            return html;
        }
        model.addAttribute("goodsList",goodsService.findGoodVo());
        model.addAttribute("user",user);
        //如果为空，手动渲染，存入redis并返回
        WebContext webContext=new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
        html=thymeleafViewResolver.getTemplateEngine().process("goodsList",webContext);
        if(!StringUtils.isEmpty(html)){
            valueOperations.set("goodList",html,60, TimeUnit.SECONDS);
        }
        return html;
    }
    /**
    * @Description: 跳转商品详情页
    * @Param: 商品id
    * @return:
    * @Author: yqy
    * @Date: 2021/10/10
    */
    @RequestMapping(value = "/toDetail2/{goodsId}",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toDetail2(@PathVariable Long goodsId,Model model,TUser user,HttpServletResponse response,HttpServletRequest request){
        //Redis中获取页面
        ValueOperations valueOperations=redisTemplate.opsForValue();
        String html=(String)valueOperations.get("goodsDetail:"+goodsId);
        if(!StringUtils.isEmpty(html)){
            return html;
        }
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
        //如果为空，手动渲染，存入redis并返回
        WebContext webContext=new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
        html=thymeleafViewResolver.getTemplateEngine().process("goodsDetail",webContext);
        if(!StringUtils.isEmpty(html)){
            valueOperations.set("goodsDetail:"+goodsId,html,60, TimeUnit.SECONDS);
        }
        return html;
      //  return "goodsDetail";
    }
    /**
     * @Description: 跳转商品详情页
     * @Param: 商品id
     * @return:
     * @Author: yqy
     * @Date: 2021/10/10
     */
    @RequestMapping("/toDetail/{goodsId}")
    @ResponseBody
    public RespBean toDetail(@PathVariable Long goodsId,TUser users){
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
        DateVo dateVo=new DateVo();
        dateVo.setGoodsVo(goodsVo);
        dateVo.setRemainSeconds(remainSeconds);
        dateVo.setSecKillStatus(secKillStatus);
        dateVo.setTuser(users);
       return RespBean.success(dateVo);
        //  return "goodsDetail";
    }
}
