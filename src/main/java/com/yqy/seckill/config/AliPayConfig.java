package com.yqy.seckill.config;

import org.springframework.context.annotation.Configuration;

/**
 * @program: seckill
 * @description: 支付宝配置类
 * @author: yqy
 * @create: 2021-10-11 15:25
 **/
@Configuration
public class AliPayConfig {
    //商家appid
    public static String APPID="2021000118628832";
    //私钥pkcs8格式的
    public static  String PSA_PRIVATE_KRY="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCna7mLlsp2yQ4G7X6m0roTRQDjJI//oZUUClktfj86zb/kV3nm+rY1h7lY2o0EwEiLW/XosIELUw9mqmOn6rxuT/Ood+ZUdfdiGswK44RonY8jNcN9dOrWmU5F1j8EubHgR+TFhL82GQGFiYeBcOmiKwp1jxhVtBW7ymKdGhnDW7FWjDJL/DUpom2kqIx4mCPGhvZsef847sLgfguui0Qlln3RzOFA0rtctny4ioJ4O7JssqVXl/OumH14Cfab3Ojfo0zNNQkrExbsmccoEXG3VCY5IG+O1OsFromVxpCEGpP1e3uDM7vFKSMnQUGskRvzFcRnz+hSvi0FGD3/PJ0tAgMBAAECggEAem3faIGjmwvZ0x0Xkfzlj7PVO0WAfAp4TwhmQEtjB6hwHBYQEeXjGvAejSh9GS1Xn3lnMXrpUxOxXn1ZlPCmTPDwmJDdgYQmnLpF3eyU5j/Z7YCibXrzRG0lfRJ4UQdOWVmZpQs3HONaZoG08uZUI3/gDSnQPNQNVJ9iT8pHbsDOsCwiK7mH7p1t5aP4f21IRxcbsA0lfmYuVvTP6QxA13kWxw7LHU0DTbqtW2jqlE38kmlY9wcbajoB+Xv0aCqNqOA8B3D9hjEn0zel3c3zaEwh12qjDaG6bb++OAYR5Pq27IcwvmfijXkySkT6y8alo+a3iN6I5VVIYo3SQXbAYQKBgQDYXr6Us0E0pPDR32Ag62KfgnDxskXWPRdtcNXZiasCnTMJKVzrgY7vXlVBhK/z++eIM0N78Yyk9x5VECM3GMVRNPQg6bScr4iWUzHsEqR0kwaqk2P3Iqp0xj+E3bzEJ/gbHhrG5KvcRUkkxK8UAOWhbJ0OKQAa1yKvEhQM1hC7tQKBgQDGFdMKehDDt/NbQkCP0GtTMf88xXsdtbu8E2fIAXA8eGa2eNWnZ9Ijgf5Z2OkBgcIbqSqkWJaNUcTzciGMxVRGXTQp61k17rAfqsj01r9YgSuUIBnRr5b3lVURl/W+/Uo1lTsrCShzEnqNFeUlIA1PK1XRVdFmrXXYZ/oIfjt2mQKBgBP1+aMCPnu607So5Ndkn76TbBQAHE09aZHhHPlqp+of0nt/42CenS8cBsV6ljjt/lgkUo92o0D8w0GZsYX9tQZe2j7HELB7ySAFIM/kkGNoxO6rlkdQXWVUfLunMSjvfwYRaur6YZh6k9regkBYLNcNjBeoVKglPL/l7Q+aGAUZAoGAWvV+mVsAA7+eghNkLiXYgrV8rN1J8ZSYBn8qss7mSP5IvBW0llC4UBHLSFqwdD6vcaA2sozzZSVdOST5fvjGACGgaHdn2bonKgN0j+lkqDF4X2Z2NLFas4nhLFkJQZBx2TUTnGlyJ5UerVaWiHu1YNtXZu7vXyTb7mmgiPHeQFECgYAW11o2uWFWzILFUmZ2yzGK6u6jRNUNxLxyDFdQSPutT6C7CS8DqAQISfFNfsUTkoMYGkoHP+pT8kpGHkKxdaBxCf4OV7fnMnAi6iTGRmQU13OmCJdcxkq6j1hcKEVV4OmuHvJy3iBAPRXs3CMWmiGbAPagetVHtXMVdCmBT/lpTQ==";
    // 服务器异步通知页面路径
    public static String notify_url="https://www.baidu.com";
    public static String return_url="https://www.baidu.com";
    //请求网关地址
    public static String URL="https://openapi.alipaydev.com/gateway.do";
    //编码
    public static String CHARSET="UTF_8";
    //返回格式
    public static String FORMAT="json";
    //支付宝公钥
    public static String ALLPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkd6bXtqDB47WS2AXyu9hmd87WjtugAfXU/r1iAvmLG/YxmWUNqiEbvgzaI1lURePC8XVCzaxiRrTnAFV82cBg7xAm4ODkHsXFUcSrgv6mWmO8fo4o7SBvMdoC93t47NA2SHQ+ARILoghslJbY8P56UOYO/MuANSfFz1F1qjtozxcpfaxRofvjuhWo4ODu61/dHUm1EHDgdf1xeTasqgTQL4U7WN/7ZmWGyjIWBwoIVkexf/6GVUxuAVKK4SOzlSY/LY36hKkN2aNHVRAV3sBbxa5i3Jm0hKudAqPsjy14VJaL+iYHNqXQUwOWWvHT2jTjSvTZ/hkY8StKL0iyV5+4QIDAQAB";
    // RSA2
    public static String SIGNTYPE="RSA2";

}
