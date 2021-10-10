package com.yqy.seckill.controller;

import com.yqy.seckill.pojo.TUser;
import com.yqy.seckill.service.impl.TUserServiceImpl;
import com.yqy.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

        model.addAttribute("user",user);
        return "goodsList";
    }
}
