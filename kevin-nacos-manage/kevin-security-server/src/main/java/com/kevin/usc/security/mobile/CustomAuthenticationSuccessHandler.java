package com.kevin.usc.security.mobile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final String INDEX_URL = "/hello";
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //super.onAuthenticationSuccess(request, response, authentication);
        log.info("登录成功!");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        /*ModelMap modelMap = new ModelMap();
        modelMap.put(String.valueOf(HttpStatus.OK.value()), " 登录成功");
        response.getWriter().write(JSON.toJSONString(modelMap));*/
        /**
         * 只有用form表单才可以进行页面跳转，
         * 如果使用ajax请求可以通过response.getWriter().write(JSON.toJSONString(modelMap))，取出url然后进行跳转
         */
        RequestCache cache = new HttpSessionRequestCache();
        SavedRequest savedRequest = cache.getRequest(request, response);
        if(null != savedRequest) {
            String url = savedRequest.getRedirectUrl();
                response.sendRedirect(url);
        }
         else{
            response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+INDEX_URL);
        }
    }
}
