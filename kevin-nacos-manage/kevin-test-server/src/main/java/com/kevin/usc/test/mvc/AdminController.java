package com.kevin.usc.test.mvc;

import com.github.pagehelper.PageInfo;
import com.kevin.usc.base.conts.CommonConstant;
import com.kevin.usc.core.bean.WResponse;
import com.kevin.usc.test.persistent.po.City;
import com.kevin.usc.test.persistent.po.Order;
import com.kevin.usc.test.service.CityService;
import com.kevin.usc.test.service.OrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdminController {



    @Autowired
    private OrderService orderService;

    @Autowired
    private CityService cityService;

    //************************************order**************************************************//

    @PostMapping("order/orderList")
    public WResponse<PageInfo<Order>> getOrderByConditions(@RequestBody Map<String, String> paramMap){

        if(StringUtils.isBlank(paramMap.get("page"))) {
            paramMap.put("page", CommonConstant.PageBean.pageStartStr);
        }

        return WResponse.offSuccess(orderService.findByExample(paramMap));
    }

    //******************************************city************************************************//
    @PostMapping("city/cityList")
    public WResponse<PageInfo<City>> getCityByConditions(@RequestBody Map<String, String> paramMap){

        if(StringUtils.isBlank(paramMap.get("page"))) {
            paramMap.put("page", CommonConstant.PageBean.pageStartStr);
        }
        return WResponse.offSuccess(cityService.findByExample(paramMap));

    }
}
