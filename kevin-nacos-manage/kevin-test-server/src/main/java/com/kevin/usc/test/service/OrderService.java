package com.kevin.usc.test.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kevin.usc.base.conts.CommonConstant;
import com.kevin.usc.test.persistent.mybatis.OrderMapper;
import com.kevin.usc.test.persistent.po.Order;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {



    @Autowired
    private OrderMapper orderMapper;



    public PageInfo<Order> findByExample(Map<String,String> paramMap) {

        PageHelper.startPage(Integer.parseInt(paramMap.get("page")), CommonConstant.PageBean.pageSizeMin);

        Example example = new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(paramMap.get("cityId"))) {
            criteria.andEqualTo("cityId", paramMap.get("cityId"));
        }
        if(StringUtils.isNotBlank(paramMap.get("startTime"))) {
            criteria.andGreaterThanOrEqualTo("startTime", paramMap.get("startTime"));
        }
        if(StringUtils.isNotBlank(paramMap.get("endTime"))) {
            criteria.andLessThanOrEqualTo("endTime", paramMap.get("endTime"));
        }
        if(StringUtils.isNotBlank(paramMap.get("status"))) {
            criteria.andEqualTo("endTime", paramMap.get("endTime"));
        }

        List<Order> orders = orderMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(orders);
        //pageInfo.getList()
        return pageInfo;
    }
}
