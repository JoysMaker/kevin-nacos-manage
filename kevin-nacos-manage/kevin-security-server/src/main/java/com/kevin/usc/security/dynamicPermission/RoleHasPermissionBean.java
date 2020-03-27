package com.kevin.usc.security.dynamicPermission;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RoleHasPermissionBean  implements Serializable {

    private static final long serialVersionUID = 7090706650114914549L;

    private String url;

    private String role;

}
