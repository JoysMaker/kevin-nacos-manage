package com.kevin.usc.security.role;

import org.springframework.security.core.Authentication;

public interface MyPermissionEvaluator {

    boolean check(Authentication authentication);
}
