package com.kevin.usc.common.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.usc.common.persistent.jpa.TUserRepository;
import com.kevin.usc.common.persistent.mybaits.TUserMapper;
import com.kevin.usc.common.persistent.po.TUser;

@Service
public class TUserService {

    @Autowired
    private TUserMapper tUserMapper;

    @Autowired
    private TUserRepository userRepository;

    public TUser getUserById(Long userId){

        return tUserMapper.selectByPrimaryKey(userId);
    }

    public void insertList(List<TUser> users){

        tUserMapper.insertList(users);
    }

    public List<TUser> findByUserName(String userName){

        return userRepository.findByUserNameContaining(userName);
    }


}
