package com.hawk.manage.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: TestController
 * @ProjectName spring-cloud-oauth2-demo
 * @Author Hawk
 * @Date 2021/1/8 20:02
 */
@RestController
public class TestController {

    @PreAuthorize("hasAuthority('sys:user:view')")
//    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/test/oauth")
    public String test() {
        return "success";
    }

    @GetMapping("/test/no_need_token")
    public String test2(){
        return "sss";
    }
}
