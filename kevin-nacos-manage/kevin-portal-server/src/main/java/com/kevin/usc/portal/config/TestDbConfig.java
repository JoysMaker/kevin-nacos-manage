package com.kevin.usc.portal.config;

import com.google.common.collect.Lists;
import com.kevin.usc.security.dynamicPermission.RoleHasPermissionBean;

import java.util.List;

public class TestDbConfig {

    public static List<RoleHasPermissionBean> roleHasPermissionList = Lists.newArrayList();

    static {
        roleHasPermissionList.add(new RoleHasPermissionBean("/tt", "ROLE_JAVA"));
        roleHasPermissionList.add(new RoleHasPermissionBean("/tt", "ROLE_PHP"));
    }

    public static void clearRoleHasPermissionList(){

        roleHasPermissionList.clear();
    }
}
