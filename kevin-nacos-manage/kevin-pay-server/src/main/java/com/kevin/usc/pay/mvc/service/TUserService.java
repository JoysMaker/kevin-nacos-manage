package com.kevin.usc.pay.mvc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.usc.core.bean.WResponse;
import com.kevin.usc.fegin.api.common.CommonService;
import com.kevin.usc.pay.persistent.jpa.TUserRepository;
import com.kevin.usc.pay.persistent.mybatis.TUserMapper;
import com.kevin.usc.pay.persistent.po.TUser;


@Service
public class TUserService {

    @Autowired
    private TUserMapper tUserMapper;

    @Autowired
    private TUserRepository userRepository;

    @Autowired
    private CommonService commonService;

    public TUser getUserById(Long userId){

        return tUserMapper.selectByPrimaryKey(userId);
    }

    public void insertList(List<TUser> users){

        tUserMapper.insertList(users);
    }

    public WResponse<List<TUser>> findByUserName(String userName){

        WResponse<List<TUser>> response = commonService.getUserByUserName(userName);
        System.out.println(response);
        return response;
        // return userRepository.findByUserNameContaining(userName);
    }



}
