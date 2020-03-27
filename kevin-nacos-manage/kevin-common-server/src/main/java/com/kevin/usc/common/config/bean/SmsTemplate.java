package com.kevin.usc.common.config.bean;

import lombok.Data;

@Data
public class SmsTemplate {
    //TYPE1@SMS_86545012@南京兆米验证码@START_CODE_KEY_
    private String type;
    private String code;
    private String sign;
    private String prefix;
    private String smstype;
}
