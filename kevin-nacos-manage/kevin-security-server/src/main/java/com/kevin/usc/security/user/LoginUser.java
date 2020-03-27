package com.kevin.usc.security.user;

import com.kevin.usc.security.role.BaseRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 子类再继承的时候 必须要赋值:userName;password;roles用于构建spring security的UserDetails
 **/
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class LoginUser implements Serializable {

    private Long userId;

    private String userName;

    private String password;

    private List<BaseRole> roles;

    public LoginUser(Long userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

}
