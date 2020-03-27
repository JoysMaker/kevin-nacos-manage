package com.kevin.usc.fegin.api.pay;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kevin.usc.fegin.api.SystemContants;

@FeignClient(value=SystemContants.ServerName.PAY_SERVER)
//@RequestMapping("fegin/pay")
public interface PayService {



    @GetMapping("pay")
    <T> T getConfigById(Long id);
}
