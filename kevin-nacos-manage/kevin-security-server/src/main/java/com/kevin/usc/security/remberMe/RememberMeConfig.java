package com.kevin.usc.security.remberMe;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.*;

@ConditionalOnProperty(prefix = "springsecurity", name ="remberme", havingValue = "true")
@Configuration
public class RememberMeConfig {

    private final DataSource dataSource;

    /**
     * 通过被@Configuration注解的构造函数的入参可以通过springIoc容器里获取，从而实现参数注入
     * @param dataSource
     */
    public RememberMeConfig(DataSource dataSource) {
        this.dataSource = dataSource;

    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 如果token表不存在，使用下面语句可以初始化该表；若存在，请注释掉这条语句，否则会报错。
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

}
