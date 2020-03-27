package com.kevin.usc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GateWayServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(GateWayServerApplication.class,args);
        System.out.println("【网关服务】已启动");
    }

}
