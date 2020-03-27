package com.kevin.usc.pay.config.db;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import com.google.common.hash.BloomFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataSourceConfig {


    @Primary
    @Bean("default")
    @ConfigurationProperties(prefix = "spring.datasource.default")
    public DataSource defaultDs(){

        return DataSourceBuilder.create().build();
    }


    @Bean("nacos")
    @ConfigurationProperties(prefix = "spring.datasource.nacos")
    public DataSource nacosDs(){
        return DataSourceBuilder.create().build();
    }

    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource(){
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object,Object> dataSourceMap = new HashMap<Object,Object>(2);
        dataSourceMap.put("default", defaultDs());
        dataSourceMap.put("nacos",nacosDs());
        dynamicRoutingDataSource.setDefaultTargetDataSource(defaultDs());
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());
        return dynamicRoutingDataSource;

    }

    /**
     * @mybatis的设置，jpa使用文件配置了
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }


    /**
     * 配置事务管理，如果使用到事务需要注入该 Bean，否则事务不会生效
     * 在需要的地方加上 @Transactional 注解即可
     *
     * @return the platform transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    public static void main(String[] args) {
        int cap  = 1<<29;
        int seed = 31;
        String value = "kevin";
        int result = 0;
        int length = value.length();
        for (int i = 0; i < length; i++) {
            result = seed * result + value.charAt(i);
            //System.out.println(result);
        }

        /*System.out.println((cap - 1) & result);
        System.out.println(10&2);
        System.out.println(cap-1);*/

        byte[] sys = "Value".getBytes();
    }
}
