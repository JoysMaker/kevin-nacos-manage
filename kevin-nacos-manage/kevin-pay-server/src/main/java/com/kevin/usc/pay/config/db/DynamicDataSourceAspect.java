package com.kevin.usc.pay.config.db;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
//设置优先级最大
@Order(-1)
@Component
public class DynamicDataSourceAspect {


    //设置切点

    /*@Pointcut("@annotation(com.kevin.usc.common.config.db.TargetDataSource)")
    private void pointCut(){}*/


    //@Before("pointCut()")
    @Before("@annotation(targetDataSource)")
    public void switchDataSource(JoinPoint point, TargetDataSource targetDataSource){

        if(!DynamicDataSourceContextHolder.dataSourceKeys.contains(targetDataSource.value())){//不填或者不存在的key值就是默认的DataSource

            System.out.println("DataSource [{}] doesn't exist, use default DataSource default"+ targetDataSource.value());

        }else {

            DynamicDataSourceContextHolder.setDataSourceKey(targetDataSource.value());
            System.out.println("Switch DataSource to [{}] in Method [{}] " +
                    DynamicDataSourceContextHolder.getDataSourceKey() + point.getSignature());

        }
    }
    @After("@annotation(targetDataSource)")
    public void restoreDataSource(JoinPoint point, TargetDataSource targetDataSource){
        // 将数据源置为默认数据源
        DynamicDataSourceContextHolder.clearDataSourceKey();
        System.out.println("Restore DataSource to [{}] in Method [{}] " +
                DynamicDataSourceContextHolder.getDataSourceKey() + point.getSignature());
    }


}

