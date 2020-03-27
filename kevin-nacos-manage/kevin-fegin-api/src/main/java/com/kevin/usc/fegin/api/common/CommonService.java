package com.kevin.usc.fegin.api.common;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kevin.usc.core.bean.WResponse;
import com.kevin.usc.fegin.api.SystemContants;

@FeignClient(value=SystemContants.ServerName.COMMON_SERVER)
//@RequestMapping("fegin/common")
public interface CommonService {

    //对应服务中COMMON-SERVER的test/findByUserName这个请求
    //把controller请求映射成接口方法
    @GetMapping(value = "/test/findByUserName")
    <T> WResponse<List<T>> getUserByUserName(@RequestParam("userName") String userName);
}
