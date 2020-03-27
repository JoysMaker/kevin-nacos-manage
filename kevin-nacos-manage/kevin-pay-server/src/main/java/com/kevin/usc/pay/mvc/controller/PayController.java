package com.kevin.usc.pay.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.kevin.usc.core.bean.WResponse;
import com.kevin.usc.fegin.api.pay.PayService;
import com.kevin.usc.pay.mvc.service.NacosService;
import com.kevin.usc.pay.mvc.service.TUserService;
import com.kevin.usc.pay.persistent.po.ConfigInfo;
import com.kevin.usc.pay.persistent.po.TUser;

@RestController
@RequestMapping("test")
public class PayController {

    @Autowired
    private TUserService userService;

    @Autowired
    private NacosService nacosService;

    @Autowired
    private PayService payService;

    @GetMapping("nacos/getconfig")
    public WResponse<ConfigInfo> testmultidb(Long id){

        return WResponse.offSuccess(nacosService.selectById(id));
    }

    @GetMapping("getById")
    public WResponse<TUser> getUserById(@RequestParam("userId") Long userId){

        return WResponse.offSuccess(userService.getUserById(userId));
    }

    @GetMapping("getByUserName")
    public WResponse<List<TUser>> getByUserName(String userName){

        return userService.findByUserName(userName);
    }

    @GetMapping("fegin/getById")
    public WResponse<ConfigInfo> getById(Long id){
        return WResponse.offSuccess(payService.getConfigById(id));
    }

}
