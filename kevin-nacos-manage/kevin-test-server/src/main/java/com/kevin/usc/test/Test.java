package com.kevin.usc.test;

import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class Test {
    private Integer age;

    private String name;

    private String addr;

    private Date birthday;

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("age",45);
        paramMap.put("name","gg");
        paramMap.put("gg","gg");
        paramMap.put("birthday",new Date());
        Test t = new Test();
        BeanUtils.populate(t, paramMap);
        System.out.println(t);
    }
}
