package com.kevin.usc.security.role;

import com.kevin.usc.security.user.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("testPermissionEvaluator")
public class TestMyPermissionEvaluatorImpl implements MyPermissionEvaluator {

    @Override
    public boolean check(Authentication authentication) {
        User user = (User)authentication.getPrincipal();
        System.out.println(user.getAuthorities());
        System.out.println(authentication);
        //这里可以拿到登陆信息然后随便的去定制自己的权限 随便你怎么查询
        //true就是过，false就是不过
        return false;
    }

    public static void main(String[] args) {
        List<String> strs = Arrays.asList("d", "b", "a", "c", "a");
        String f = strs.stream().filter(t -> t.equals("a")).findFirst().orElse(null);
         System.out.println(f);
    }
}
