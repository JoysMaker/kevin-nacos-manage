package com.kevin.usc.security.user;

public interface LoginUserService<T extends LoginUser> {

    T findByUserName(String userName);
}
