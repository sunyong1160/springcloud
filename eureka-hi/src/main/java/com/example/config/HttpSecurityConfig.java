//package com.example.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// *
// */
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class HttpSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                //以"/css","/index"资源不需要验证，外界请求可以直接访问这些资源
//                .antMatchers("/css/**","/index/**").permitAll()
//                //以"/user"开头的资源需要验证，并且需要用户的角色是“Role”
//                .antMatchers("/user/**").hasRole("USER")
//                .and()
//                //表单登录的地址是login，登录失败的地址是login-error
//                .formLogin().loginPage("/login").failureUrl("/login-error")
//                .and()
//                //异常处理会重定向到"/401"界面
//                .exceptionHandling().accessDeniedPage("/401");
//        http.logout().logoutSuccessUrl("/");
//    }
//}
