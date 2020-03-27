package com.kevin.usc.security.dynamicPermission;

import java.util.List;

public interface RoleHasPermissionService {

    List<RoleHasPermissionBean> selectRoleWithPermission();

}
