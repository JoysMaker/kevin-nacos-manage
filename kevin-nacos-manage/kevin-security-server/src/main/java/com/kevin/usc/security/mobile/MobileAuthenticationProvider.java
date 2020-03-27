package com.kevin.usc.security.mobile;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * 短信登陆鉴权 Provider，要求实现 AuthenticationProvider 接口
 */
public class MobileAuthenticationProvider implements AuthenticationProvider {

    public static final String SPRING_SECURITY_FORM_MOBILE_KEY = "mobile";

    public String mobileParameter = SPRING_SECURITY_FORM_MOBILE_KEY;
    //上下文的userDetailsService
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //1.获取未鉴权的token
        SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) authentication;
        String mobile = (String)smsCodeAuthenticationToken.getPrincipal();
        //校验手机号
        checkSmsCode(mobile);
        UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);
        // 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationResult.setDetails(smsCodeAuthenticationToken.getDetails());
        return authenticationResult;
    }

    protected  void checkSmsCode(String mobile) {
        HttpServletRequest request =((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String inputCode = request.getParameter("smsCode");

        if(inputCode == null){
            throw new BadCredentialsException("验证码不能为空");
        }
        //这里的验证码我们放session里，这里拿出来跟用户输入的做对比
        Map<String, Object> codeMap = (Map<String, Object>)request.getSession().getAttribute("mobileInfo");
        if (codeMap == null) {
            throw new BadCredentialsException("未检测到申请验证码");
        }
        //对比电话号码
        String applyMobile = (String)codeMap.get(mobileParameter);
        if(StringUtils.isBlank(applyMobile) || !StringUtils.equals(applyMobile, mobile)) {
            throw new BadCredentialsException("申请的手机号码与登录手机号码不一致");
        }

        //对比验证码
        int smsCode = (int) codeMap.get("code");
        if(smsCode != Integer.parseInt(inputCode)) {
            throw new BadCredentialsException("验证码不正确");
        }


    }
    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或子接口
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

}
