package com.kevin.usc.security.role;

import com.kevin.usc.security.user.LoginUser;
import com.kevin.usc.security.user.LoginUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service("extendLoginUserService")
public class RoleLoginUserServiceImpl implements LoginUserService {

    @Override
    public LoginUser findByUserName(String userName) {

        return InitData.SYS_USERS.stream().filter(o -> StringUtils.equals(o.getUserName(),userName)).findFirst().orElse(null);

    }
}
