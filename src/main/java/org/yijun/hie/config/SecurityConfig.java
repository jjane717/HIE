package org.yijun.hie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.yijun.hie.service.MethodSecurityService;

import javax.sql.DataSource;

/**
* Created by liuyijun on 11/15/14.
*/

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public MethodSecurityService methodSecurityService(){
        return new MethodSecurityService();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .inMemoryAuthentication()
                .withUser("aaa").password("aaaaa").roles("USER").and()
                .withUser("bbb").password("bbbbb").roles("ADMIN");
    }

//    @Autowired
//    public void configureGlobal(DataSource dataSource, AuthenticationManagerBuilder auth) throws Exception{
//        auth
//                .jdbcAuthentication()
//                    .dataSource(dataSource)
//                    .withDefaultSchema()
//                    .withUser("aaa").password("aaaaa").roles("USER").and()
//                    .withUser("bbb").password("bbbbb").roles("ADMIN");
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/createUserAccount").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    //.failureUrl("/login?login_error=1")
                    .defaultSuccessUrl("/hiUser")
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/login");
//                    .permitAll();

    }
}
