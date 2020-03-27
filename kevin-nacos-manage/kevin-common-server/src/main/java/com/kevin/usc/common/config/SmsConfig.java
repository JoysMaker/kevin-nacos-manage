package com.kevin.usc.common.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.kevin.usc.common.config.bean.SmsTemplate;
import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "sms")
@Data
public class SmsConfig {

    List<SmsTemplate> smsTemplates;

}
