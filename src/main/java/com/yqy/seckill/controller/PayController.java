package com.yqy.seckill.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.yqy.seckill.config.AliPayConfig;
import com.yqy.seckill.mapper.TOrderMapper;
import com.yqy.seckill.pojo.TUser;
import com.yqy.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @program: seckill
 * @description: 支付宝支付控制层
 * @author: yqy
 * @create: 2021-10-11 17:07
 **/
@Controller
@RequestMapping(value = "/alipay")
public class PayController {
    @Autowired
    private TOrderMapper orderMapper;
    @RequestMapping(value = "/pay")
    public String alipayPay(GoodsVo goodsVo) throws AlipayApiException {
        AlipayClient alipayClient=new DefaultAlipayClient(AliPayConfig.URL,
                AliPayConfig.APPID,AliPayConfig.PSA_PRIVATE_KRY,AliPayConfig.FORMAT,AliPayConfig.CHARSET,
                AliPayConfig.ALLPAY_PUBLIC_KEY,AliPayConfig.SIGNTYPE);
        AlipayTradeWapPayRequest request=new AlipayTradeWapPayRequest();
        AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
        model.setOutTradeNo("1");
        model.setSubject("1");
        model.setTotalAmount("1");
        model.setBody("1");
        model.setTimeoutExpress("3m");
        model.setProductCode("1");
        request.setBizModel(model);
        request.setNotifyUrl(AliPayConfig.notify_url);
        request.setReturnUrl(AliPayConfig.return_url);
        String result = alipayClient.pageExecute(request).getBody();
        return "/"+result;
    }
    /**
     * 支付宝服务器异步通知页面
     * @param request
     * @param out_trade_no 商户订单号
     * @param trade_no 支付宝交易凭证号
     * @param trade_status 交易状态
     * @return
     * @throws AlipayApiException
     */
    @PostMapping("/notify")
    public String alipayNotify(HttpServletRequest request, String out_trade_no, TUser user, String trade_no, String trade_status) throws AlipayApiException {
        Map<String, String> params = getParamsMap(request);
        // 验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AliPayConfig.ALLPAY_PUBLIC_KEY, AliPayConfig.CHARSET, AliPayConfig.SIGNTYPE);
        if (signVerified) {
            //处理你的业务逻辑，更细订单状态等
            orderMapper.updateStatus(Long.valueOf(out_trade_no),user.getId(),1);
            return ("success");
        } else {
           // logger.info("验证失败,不去更新状态");
            return ("fail");
        }
    }

    /**
     * 支付宝服务器同步通知页面
     * @param request
     * @param out_trade_no 商户订单号
     * @param trade_no 支付宝交易凭证号
     * @param total_amount 交易状态
     * @return
     * @throws AlipayApiException
     */
    @GetMapping("/return")
    public String alipayReturn(HttpServletRequest request, String out_trade_no,String trade_no,String total_amount) throws AlipayApiException {
        Map<String, String> params = getParamsMap(request);
        //logger.info("return params: {}", JSONObject.toJSON(params));

        // 验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AliPayConfig.ALLPAY_PUBLIC_KEY, AliPayConfig.CHARSET, AliPayConfig.SIGNTYPE);
        //logger.info("return signVerified: {}", signVerified);

        if (signVerified) {

            return ("success");
        } else {
          //  logger.info("验证失败,不去更新状态");
            return ("fail");
        }
    }

    private Map<String, String> getParamsMap(HttpServletRequest request) {
        Map<String,String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            try {
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return params;
    }

}
