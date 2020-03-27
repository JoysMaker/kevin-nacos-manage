package com.kevin.usc.portal.service;

import com.google.common.collect.Lists;
import com.kevin.usc.portal.config.TestDbConfig;
import com.kevin.usc.security.dynamicPermission.RoleHasPermissionBean;
import com.kevin.usc.security.dynamicPermission.RoleHasPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleHasPermissionServiceImpl implements RoleHasPermissionService {

    @Override
    public List<RoleHasPermissionBean> selectRoleWithPermission() {

        return  TestDbConfig.roleHasPermissionList;

    }
}
