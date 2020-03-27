package com.kevin.usc.common.mvc.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kevin.usc.common.config.SmsConfig;
import com.kevin.usc.common.mvc.service.TUserService;
import com.kevin.usc.common.persistent.po.TUser;
import com.kevin.usc.core.bean.WResponse;

@RestController
@RequestMapping("test")
public class CommonController {

    @Autowired
    private SmsConfig smsConfig;

    @Autowired
    private TUserService userService;

    @GetMapping("sms")
    public WResponse<Object> getSmsConfig(){

        return WResponse.offSuccess(smsConfig.getSmsTemplates());
    }
    @GetMapping("getById")
    public WResponse<TUser> getUserById(@RequestParam("userId") Long userId){

        return WResponse.offSuccess(userService.getUserById(userId));
    }

    @PostMapping("inserts")
    public WResponse<String> inserts(@RequestBody JSONObject jsonObject){

        JSONArray jsonArray = jsonObject.getJSONArray("users");
        List<TUser> users = JSONObject.parseArray(jsonArray.toJSONString(), TUser.class);
        userService.insertList(users);

        return WResponse.offSuccess(null);
    }

    @GetMapping("findByUserName")
    public WResponse<List<TUser>> findByUserName(@RequestParam("userName") String userName){

        return WResponse.offSuccess(null);
    }

    public static void main(String[] args) {

        int size = 5;

           // System.out.println(DateTime.now());
        for (int i=0;i< 5;i++){
            DateTime month = DateUtil.offsetMonth(DateTime.now(), i);
            System.out.println(DateUtil.format(month,"yyyy-MM"));
        }
        System.out.println("++++++++++++++++++++++++=======");
        for (int i=0;i>- 5;i--){
            DateTime month = DateUtil.offsetMonth(DateTime.now(), i);
            System.out.println(month);
        }
        //DateTime month = DateUtil.offsetMonth(DateTime.now(), -5);
        //System.out.println(month);
    }
}
