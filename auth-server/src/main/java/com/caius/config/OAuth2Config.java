package com.caius.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;


//AuthorizationServerConfigurerAdapter是Spring-security的核心部分，
//它提供了执行关键验证和授权功能的基本机制
@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;


    //这定义了哪些客户端将注册到服务
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("batman")
                //secret是密钥，应用程序调用时提供
                .secret("{noop}thisBatman")
                //它被传入一个逗号分隔的授权类型列表
                //这些授权类型将由oauth2支持
                //下面的列表显示在这个服务中，密码授权和客户端凭证
                .authorizedGrantTypes(
                        "refresh_token",
                        "password",
                        "client_credentials")
                //通过定义与作用域，可以编写特定于客户端应用程序所工作的作用域的授权规则
                .scopes("webclient", "mobileclient");
    }

    //告诉Spring使用Spring提供的默认的验证管理器和用户详情信息服务
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

}
