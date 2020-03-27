package com.kevin.usc.base.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class ApplicationContextBean implements ApplicationContextAware, InitializingBean {

    private static  ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        ApplicationContextBean.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Arrays.asList(applicationContext.getBeanDefinitionNames())
              .forEach(beanName-> System.err.println(beanName));

    }


}
