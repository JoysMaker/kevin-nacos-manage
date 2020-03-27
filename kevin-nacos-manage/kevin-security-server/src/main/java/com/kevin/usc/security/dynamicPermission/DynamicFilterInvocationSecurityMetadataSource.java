package com.kevin.usc.security.dynamicPermission;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

//定义一个自己的 SecurityMetadataSource,然后每次调用的时候都去数据库里去查询。
/**
 * 假设这就是从数据库中查询到的数据
 * 意思就是 ROLE_JAVA 的角色 才能访问 /tt
 */
public class DynamicFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static List<RoleHasPermissionBean> urlRoleList = Lists.newArrayList();
    static {

        urlRoleList.add(new RoleHasPermissionBean("/tt", "ROLE_JAVA"));
        urlRoleList.add(new RoleHasPermissionBean("/tt", "ROLE_DOCKER"));
    }

    private RoleHasPermissionService roleHasPermissionService;

    private FilterInvocationSecurityMetadataSource superMetadataSource;

    private static Map<String, String> permissionMap = Maps.newHashMap();

    private static List<RoleHasPermissionBean> role_permission_list = Lists.newArrayList();
    public void setRoleHasPermissionService(RoleHasPermissionService roleHasPermissionService){

        this.roleHasPermissionService = roleHasPermissionService;
        //在这里去查询你的数据库 构造成("/tt", "ROLE_JAVA")这种样子
        if (null != roleHasPermissionService) {//被注入后可以从数据库中查询角色所拥有的的权限
            List<RoleHasPermissionBean> roleHasPermissionBeans = null;
            if(role_permission_list.size() == 0) {//如果role_permission_list已经初始化了，就没有必要再初始化了

                role_permission_list = this.roleHasPermissionService.selectRoleWithPermission();
                //把权限放在map 或者redis缓存中 redis，
                // 1.先从redis里面取
                // 2.再从list缓存里面取(内存)
                //TODO 先判断有没有开启redis缓存保存

            }

        }else{//测试用

            role_permission_list = urlRoleList;
        }
    }
    public DynamicFilterInvocationSecurityMetadataSource(){}
    public DynamicFilterInvocationSecurityMetadataSource(FilterInvocationSecurityMetadataSource expressionBasedFilterInvocationSecurityMetadataSource) {
        this.superMetadataSource = expressionBasedFilterInvocationSecurityMetadataSource;
    }

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();



    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        List<String> attributeNames = Lists.newArrayList();
        for (int i = 0; i < role_permission_list.size(); i++) {
            if (antPathMatcher.match(role_permission_list.get(i).getUrl(), url)) {

                attributeNames.add(role_permission_list.get(i).getRole());
            }

        }

        if(attributeNames.size() != 0) {
            return SecurityConfig.createList(attributeNames.toArray(new String[attributeNames.size()]));
        }
        //如果没有匹配到就拿 咱们自定义的配置
        return superMetadataSource.getAttributes(object);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    public static void clearPermissionMap() {

        permissionMap.clear();
    }

    //防止权限重新变动
    public void reSetPermissionList() {
        List<RoleHasPermissionBean> roleHasPermissionBeans = roleHasPermissionService.selectRoleWithPermission();
        role_permission_list = roleHasPermissionBeans;
    }

    //TODO 可以添加缓存机制把权限和角色放入缓存map中
}
