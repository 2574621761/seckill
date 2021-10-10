package com.yqy.seckill.controller;

import com.yqy.seckill.service.ITUserService;
import com.yqy.seckill.service.impl.TUserServiceImpl;
import com.yqy.seckill.vo.LOginVo;
import com.yqy.seckill.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private ITUserService userService;
    @RequestMapping(value = "/tologin")
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/doLogin")
    @ResponseBody
    public RespBean login(@Valid LOginVo lOginVo, HttpServletRequest request, HttpServletResponse response){
        return userService.login(lOginVo,request,response);
    }
}
