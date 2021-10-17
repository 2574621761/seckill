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
    public static String APPID="2021002186655404";
    //私钥pkcs8格式的
    public static  String PSA_PRIVATE_KRY="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCuAyNbzdByBZZwQ7XN+Q8r1Fdx4flW5xcsV6HPukj7fpQ6xUuOoMSXN32rBv7k41cq2qF52NoqfeX3jyL6Z9C/nsBvvMI1YxChztvLq29zMt1qmm5aorl2oCNLYYBsOSrW+gwmg+IVvCygUW1W8PwFs8oFAleGBjmL74RujfWHb2mz2j4yToOFJNYXQcAJBxXi6caRj1iq2hQ73f1oNas/G++2V6i3vYHfXF7bGDojsKgZsEB4+KgNOn8srjNlKW3Ha1y0wUl1LmjlusN8m6/2y2ylG0iK+lveu0ewKD2tuFPLwiURIh9Tkf1FGIROxDuPJpP2okUmUTl1w11zyN8vAgMBAAECggEABF4lzA8ddU84mHbKoHHo9JOu4jfm10yTo1RLxngJju59cAA1xk24wYbuC5XwXfRq4rN8LB/KUhi45f7cxOZIMhcdA3D8evSSnU0g7TrPYFfdgOiBbpr5BsY8PI8k9xJ0RdMe7PSQfzUq9Zc1pGIGIWqC3p9RbRbI6P9JFrFPgWe6x/qIasWDt8sJVD1zhyNvuRWjm/9PtJJe14a6fvsxvGY6VKrjcStyINy6U6bAzym+QpaTBPx15Da2U2p70jBpAuw3C+84UprATxRwfTHSTf5cAJv8PVEBycrDc7WevjaULjSzNzMVA5n72YBbfseJbyhmzr5T0UOCmwvlZPDc6QKBgQD3QHH4kPzgXYw+MLb/v5rWkt/mNrTmfJe6N5ZRIcsHZiLmOypkZL/jxYZRoYOBRWl3tjGpop6odWwwXU3BLPpgJ0kw3wx5IaSdPwQt5VQ15Dzdptuz6GNJavz7dqEru+Zmi7j6z4jzIh1v8CiMIZb3OAYhlWpANyLVwifBho1HhQKBgQC0K04Q05iGeN6a0HpKi0HIpZMvAEsB/qBwcUhylYVzgxMRbyjkc4iK6U3aHxzSF531+nmDw5oiWP7vFPhMJIoPwJ+btFyJFAhUoDdaC1Nh0vWU6WWTf7EzUeMkd5wqF5YbMUUrhFB2oVk/cdnfGZA1wmBpzEQ56OfBxFZJyHc4IwKBgQDsu8KkCUZmGIu32/LLioxZWIWSKd0pNjAcNy7wfZekfPF7WTjsqN5l2nER5cBA3NHvsf14+2BH0S4GXL+e707HE7ZrWZTawhCYEocQ3puzgdWZMDL53c56tDqHFn2Yny8qDivN0TnB+W8QB3T7PwoJqJWOQD5cP65V1rE0BX1t9QKBgBuYfm6JWlVOchoYUILWbY8D/VU3oEV/8KcIUuZqDHSy4NrMweCr6YANWAVOuqVbRLCGWtJZxWP5aaLI/Xb8KcFb6ZZ5u6qrTNNZbuD11yGmpELYtNwwtL19j2LohxzTXARyOXv+2iwxbnAPSiWnzrTNPuM1jEWqbMT7A5RthgUtAoGBAKN8Gy5163vmYr9aaH5vBHGYoCxarJKI7CUrGGaV2BvbpWSthYBxX/oZa1AZKE4xVVDFHmEfKK5wFm37T84p+tjVx1rC9CuJt1In9v/d14VLM5WZNT+j0Yfr3EMbq00ooOOfarnaQhKTyEi0tyTZKQ5gqeF9PJ6vRzRAmy+iEaTU";
    // 服务器异步通知页面路径
    public static String notify_url="https://www.baidu.com";
    public static String return_url="https://www.baidu.com";
    //请求网关地址
    public static String URL="https://openapi.alipay.com/gateway.do";
    //编码
    public static String CHARSET="UTF-8";
    //返回格式
    public static String FORMAT="json";
    //支付宝公钥
    public static String ALLPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkd6bXtqDB47WS2AXyu9hmd87WjtugAfXU/r1iAvmLG/YxmWUNqiEbvgzaI1lURePC8XVCzaxiRrTnAFV82cBg7xAm4ODkHsXFUcSrgv6mWmO8fo4o7SBvMdoC93t47NA2SHQ+ARILoghslJbY8P56UOYO/MuANSfFz1F1qjtozxcpfaxRofvjuhWo4ODu61/dHUm1EHDgdf1xeTasqgTQL4U7WN/7ZmWGyjIWBwoIVkexf/6GVUxuAVKK4SOzlSY/LY36hKkN2aNHVRAV3sBbxa5i3Jm0hKudAqPsjy14VJaL+iYHNqXQUwOWWvHT2jTjSvTZ/hkY8StKL0iyV5+4QIDAQAB";
    // RSA2
    public static String SIGNTYPE="RSA2";

}
