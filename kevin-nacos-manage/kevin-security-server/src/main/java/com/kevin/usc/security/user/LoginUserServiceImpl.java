package com.kevin.usc.security.user;


import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

@Service
public class LoginUserServiceImpl implements LoginUserService<LoginUser> {

    private static final Set<LoginUser> users = Sets.newHashSet();

    static {
        users.add(new LoginUser(1L, "fulin", "123456"));
        users.add(new LoginUser(2L, "xiaohan", "123456"));
        users.add(new LoginUser(3L, "longlong", "123456"));
        //手机登录模拟数据
        users.add(new LoginUser(3L, "13913395279", "123456"));
    }
    @Override
    public LoginUser findByUserName(String userName) {
        return users.stream().filter(u -> StringUtils.equals(u.getUserName(), userName)).findFirst().orElse(null);
    }
}
