package com.kevin.usc.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.kevin.usc.portal","com.kevin.usc.security","com.kevin.usc.base"})
@EnableDiscoveryClient
public class PortalServerApplication {

    public static void main(String[] args) {

        try{
            //System.setProperty("spring.devtools.restart.enabled", "false");
            SpringApplication.run(PortalServerApplication.class, args);
            System.out.println("【portal服务】已启动");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
