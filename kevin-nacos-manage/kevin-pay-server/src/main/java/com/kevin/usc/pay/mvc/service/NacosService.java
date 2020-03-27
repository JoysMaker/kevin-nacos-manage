package com.kevin.usc.pay.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kevin.usc.pay.config.db.TargetDataSource;
import com.kevin.usc.pay.persistent.mybatis.ConfigInfoMapper;
import com.kevin.usc.pay.persistent.po.ConfigInfo;

@Service
public class NacosService {

   @Autowired
   private ConfigInfoMapper configInfoMapper;

   @TargetDataSource("nacos")
   public ConfigInfo selectById(Long id){

       return configInfoMapper.selectByPrimaryKey(id);

   }

}
