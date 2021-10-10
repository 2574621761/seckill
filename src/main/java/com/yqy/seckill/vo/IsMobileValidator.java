package com.yqy.seckill.vo;


import com.yqy.seckill.validator.IsMobile;
import com.yqy.utils.ValidatorUtil;
import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * @program: seckill
 * @description: 手机号码校验规则
 * @author: yqy
 * @create: 2021-10-09 15:27
 **/
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {
    private boolean required=false;
    @Override
    public void initialize(IsMobile constraintAnnotation) {
       boolean required=constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required){
            return ValidatorUtil.ismobile(s);
        }else {
            if(StringUtils.isEmpty(s)){
                return true;
            }else {
                return ValidatorUtil.ismobile(s);
            }
        }
    }
}
