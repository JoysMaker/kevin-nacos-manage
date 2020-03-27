package com.kevin.usc.test.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kevin.usc.base.conts.CommonConstant;
import com.kevin.usc.test.persistent.mybatis.CityMapper;
import com.kevin.usc.test.persistent.po.City;
import com.kevin.usc.test.persistent.po.Order;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    public PageInfo<City> findByExample(Map<String, String> paramMap){

        PageHelper.startPage(Integer.parseInt(paramMap.get("page")), CommonConstant.PageBean.pageSizeMin);

        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(paramMap.get("cityId"))) {
            criteria.andEqualTo("cityId", paramMap.get("cityId"));
        }
        if(StringUtils.isNotBlank(paramMap.get("mode"))) {
            criteria.andEqualTo("mode", paramMap.get("mode"));
        }
        if(StringUtils.isNotBlank(paramMap.get("opMode"))) {
            criteria.andEqualTo("opMode", paramMap.get("opMode"));
        }
        if(StringUtils.isNotBlank(paramMap.get("fianchiseeStatus"))) {
            criteria.andEqualTo("fianchiseeStatus", paramMap.get("fianchiseeStatus"));
        }

        List<City> citys = cityMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(citys);
        //pageInfo.getList()
        return pageInfo;
    }
}
