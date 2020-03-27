package com.kevin.usc.portal.mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionController {
    @RequestMapping("/docker")
    public String test1() {
        return "说明你有docker权限";
    }
    @RequestMapping("/custom")
    public String test0() {
        return "说明你有自定义权限";
    }
    @RequestMapping("/java")
    public String test2() {
        return "说明你有java权限";
    }
    @RequestMapping("/php")
    public String test3() {
        return "说明你有最好语言的权限";
    }
}