package com.caius;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableResourceServer
//这个注解告诉Spring-cloud，该服务将用于oauth服务
//并添加几个端点，这几个端点将在验证和授权过程中使用
@EnableAuthorizationServer
public class OauthServerApplication {

    /**
     * 此方法由受保护服务的调用，以确认oanth2访问令牌，
     * 并检索访问受保护服务的用户所分配的角色
     *
     * @param user 请求的用户信息
     * @return 返回用户信息
     */
    @GetMapping(value = "/user", produces = "application/json")
    public Map<String, Object> user(OAuth2Authentication user) {

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        return userInfo;
    }


    public static void main(String[] args) {
        SpringApplication.run(OauthServerApplication.class, args);
    }
}
