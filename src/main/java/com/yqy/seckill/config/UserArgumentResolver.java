package com.yqy.seckill.config;

import com.yqy.seckill.pojo.TUser;
import com.yqy.seckill.service.impl.TUserServiceImpl;
import com.yqy.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: seckill
 * @description: 自定义用户参数
 * @author: yqy
 * @create: 2021-10-09 23:14
 **/
@Configuration
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private TUserServiceImpl userService;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
       Class<?> aClass=parameter.getParameterType();
       return aClass==TUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request=webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response=webRequest.getNativeResponse(HttpServletResponse.class);
        String ticket=CookieUtil.getCookieValue(request,"userTicket");
        if (ticket==null){
            return null;
        }
        return userService.getUserByCookie(ticket,request,response);
    }
}
