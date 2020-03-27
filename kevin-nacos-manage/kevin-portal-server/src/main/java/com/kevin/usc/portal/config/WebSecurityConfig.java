package com.kevin.usc.portal.config;

import com.kevin.usc.security.dynamicPermission.DynamicFilterInvocationSecurityMetadataSource;
import com.kevin.usc.security.dynamicPermission.RoleHasPermissionService;
import com.kevin.usc.security.mobile.SmsCodeAuthenticationSecurityConfig;
import com.kevin.usc.security.password.FailureAuthenticationHandler;
import com.kevin.usc.security.password.SuccessAuthenticationHandler;
import com.kevin.usc.security.role.AccessDeniedAuthenticationHandler;
import com.kevin.usc.security.role.CustomPermissonEvaluator;
import com.kevin.usc.security.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FailureAuthenticationHandler failureAuthenticationHandler;
    @Autowired
    private SuccessAuthenticationHandler successAuthenticationHandler;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private UserService userService;
    @Autowired
    private PersistentTokenRepository persistentTokenRepository;
    @Autowired
    private AccessDeniedAuthenticationHandler accessDeniedAuthenticationHandler;
    @Autowired
    private AccessDecisionManager accessDecisionManager;
    @Autowired
    private RoleHasPermissionService roleHasPermissionService;


    /**
     * Spring Security默认是禁用注解的，要想开启注解，
     * 要在继承WebSecurityConfigurerAdapter的类加@EnableMethodSecurity注解，
     * 并在该类中将AuthenticationManager定义为Bean。
     * 注入身份管理器bean
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DynamicFilterInvocationSecurityMetadataSource mySecurityMetadataSource(FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource) {
        DynamicFilterInvocationSecurityMetadataSource securityMetadataSource = new DynamicFilterInvocationSecurityMetadataSource(filterInvocationSecurityMetadataSource);
        //在setRolePermissionService时,同时权限和角色进行装配
        securityMetadataSource.setRoleHasPermissionService(roleHasPermissionService);
        return securityMetadataSource;
    }

    /**
     * 注入自定义权限管理
     *
     * @return
     * @throws Exception
     */
    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new CustomPermissonEvaluator());
        return handler;
    }
    /**
     * 把userService 放入AuthenticationManagerBuilder 里
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(
                new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence charSequence) {
                        return charSequence.toString();
                    }

                    @Override
                    public boolean matches(CharSequence charSequence, String s) {
                        return s.equals(charSequence.toString());
                    }
                });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.httpBasic()  //httpBasic 登录
        http.formLogin()
                .loginPage("/login")// 登陆的url
                .loginProcessingUrl("/authentication/form") // 自定义登录路径
                //.defaultSuccessUrl("/hello")//设置登录成功后默认跳转页
                .failureHandler(failureAuthenticationHandler) // 自定义登录失败处理
                .successHandler(successAuthenticationHandler) // 自定义登录成功处理
                .and()
                .sessionManagement() //设置session的超时时间
                .invalidSessionUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .authorizeRequests()// 对请求授权
                // 自定义FilterInvocationSecurityMetadataSource
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                        fsi.setSecurityMetadataSource(mySecurityMetadataSource(fsi.getSecurityMetadataSource()));
                        fsi.setAccessDecisionManager(accessDecisionManager);
                        return fsi;
                    }
                })
                 // 这些页面不需要身份认证,其他请求需要认证
                .antMatchers("/login","/sms/**", "/authentication/require",
                        "/authentication/form","/logout","/session/out").permitAll()
                 // 用户拥有制定的角色时返回true （Spring security默认会带有ROLE_前缀）
                .antMatchers("/docker").hasRole("DOCKER")
                .antMatchers("/java").hasRole("JAVA")
                .antMatchers("/custom").access("@testPermissionEvaluator.check(authentication)")
                .anyRequest() // 任何请求
                .authenticated() // 都需要身份认证
                //配置权限失败处理器
                .and().exceptionHandling().accessDeniedHandler(accessDeniedAuthenticationHandler)
                .and()
                //记住我的功能(数据库版)
                .rememberMe()
                .tokenRepository(persistentTokenRepository)
                .rememberMeCookieName("rememberMe")//默认名称是remember-me
                .tokenValiditySeconds(3000)
                .and()
                .csrf().disable(); // 禁用跨站攻击

        /**
         *添加手机验证码登录方式
         */
        http.apply(smsCodeAuthenticationSecurityConfig);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

}