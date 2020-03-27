package com.kevin.usc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.kevin.usc.test.persistent.mybatis")
public class TestServerApplication {

    public static void main(String[] args) {
            SpringApplication.run(TestServerApplication.class, args);
            System.out.println("[test服务] 启动了");

    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }

}
