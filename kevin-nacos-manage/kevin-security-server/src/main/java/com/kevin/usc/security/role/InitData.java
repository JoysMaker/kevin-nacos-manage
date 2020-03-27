package com.kevin.usc.security.role;
import com.google.common.collect.Sets;
import com.kevin.usc.security.user.LoginUser;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class InitData {

    public static final Set<LoginUser> SYS_USERS = Sets.newHashSet();

    public static final Set<BaseRole>  SYS_ROLES = Sets.newHashSet();

    static {

        SYS_ROLES.add(new BaseRole(1L, "ROLE_JAVA"));
        SYS_ROLES.add(new BaseRole(2L, "ROLE_DOCKER"));
        SYS_ROLES.add(new BaseRole(3L, "ROLE_PHP"));
        SYS_ROLES.add(new BaseRole(4L, "ROLE_PYTHON"));
        SYS_ROLES.add(new BaseRole(5L, "ROLE_CENTOS"));
    }

    static {
        SYS_USERS.add(
                new LoginUser(1L, "fulin", "123456",
                        SYS_ROLES.stream().filter(o -> StringUtils.equalsAny(o.getRoleName(), "ROLE_JAVA", "ROLE_C++")).collect(Collectors.toList())
                )
        );
        SYS_USERS.add(
                new LoginUser(2L, "maoxiansheng", "123456",
                        SYS_ROLES.stream().filter(o -> StringUtils.equalsAny(o.getRoleName(), "ROLE_PHP", "ROLE_DOCKER")).collect(Collectors.toList())
                )
        );
        SYS_USERS.add(
                new LoginUser(3L, "happy fish", "123456",
                        SYS_ROLES.stream().filter(o -> StringUtils.equalsAny(o.getRoleName(), "ROLE_PYTHON", "ROLE_CENTOS")).collect(Collectors.toList())
                )
        );
    }
}
