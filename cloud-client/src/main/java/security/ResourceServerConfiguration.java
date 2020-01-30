package security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

//这个类必须用下面这个注解进行标记
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    //所有访问规则都是在覆盖的configure()方法进行定义
    @Override
    //所有访问规则都是通过传入方法的HttpSecurity对象配置的
    //如果访问该服务的时候没有在头部添加令牌，那么就会返回错误吗401以及提示需要令牌
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
    }
}
