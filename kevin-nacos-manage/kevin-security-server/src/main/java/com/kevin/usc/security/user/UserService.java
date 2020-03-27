package com.kevin.usc.security.user;

import com.kevin.usc.security.role.BaseRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    //@Autowired
    private LoginUserService loginUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser loginUser = loginUserService.findByUserName(username);
        if(loginUser == null ){
            throw new UsernameNotFoundException("用户不存在");
        }
      //把角色放入认证器中
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<BaseRole> roles = loginUser.getRoles();
        for(BaseRole role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        //构造UserDetails并且返回
        return new User(loginUser.getUserName(), loginUser.getPassword(), authorities);
    }

    /**
     * @Resource默认按照名称装配，当找不到与名称匹配的bean才会按照类型装配，可以通过name属性指定，如果没有指定name属 性，
     * 当注解标注在字段上，即默认取字段的名称作为bean名称寻找依赖对象，当注解标注在属性的setter方法上，即默认取属性名作为bean名称寻找 依赖对象.
     * 注意：如果没有指定name属性，并且按照默认的名称仍然找不到依赖的对象时候，会回退到按照类型装配，但一旦指定了name属性，就只能按照名称 装配了.
     * @param "ExtendLoginUserService"
     */
    @Resource
    public void setExtendLoginUserService(LoginUserService loginUserService) {

        this.loginUserService = loginUserService;
    }
}
