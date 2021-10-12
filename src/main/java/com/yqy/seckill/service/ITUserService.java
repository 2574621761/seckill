package com.yqy.seckill.service;

import com.yqy.seckill.pojo.TUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqy.seckill.vo.LOginVo;
import com.yqy.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yqy
 * @since 2021-10-07
 */
public interface ITUserService extends IService<TUser> {
/**
* @Description: 登录功能
* @Param:
* @return:
* @Author: yqy
* @Date: 2021/10/12
*/
    RespBean login(LOginVo lOginVo, HttpServletRequest request, HttpServletResponse response);
    /**
    * @Description: 根据cookie获取session
    * @Param:
    * @return:
    * @Author: yqy
    * @Date: 2021/10/12
    */
    TUser getUserByCookie(String userTickte,HttpServletRequest request,HttpServletResponse response);
    /**
    * @Description: 更新密码
    * @Param:
    * @return:
    * @Author: yqy
    * @Date: 2021/10/12
    */
    RespBean updatePassword(String userTickte,String password,HttpServletRequest request,HttpServletResponse respons);
}
