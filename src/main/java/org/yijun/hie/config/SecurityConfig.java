package org.yijun.hie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.yijun.hie.service.MethodSecurityService;
import org.yijun.hie.service.MyUserDetailsService;

/**
* Created by liuyijun on 11/15/14.
*/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackageClasses = MyUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public MethodSecurityService methodSecurityService(){
        return new MethodSecurityService();
    }

    @Autowired
    private UserDetailsService userDetailsService;

//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth
//                .inmemeory
//                .withDefaultSchema()
//                .withUser("aaa").password("aaaaa").roles("USER").and()
//                .withUser("bbb").password("bbbbb").roles("ADMIN");
//    }
//
////    @Autowired
////    public void configureGlobal(DataSource dataSource, AuthenticationManagerBuilder auth) throws Exception{
////        auth
////                .jdbcAuthentication()
////                    .dataSource(dataSource)
////                    .withDefaultSchema()
////                    .withUser("aaa").password("aaaaa").roles("USER").and()
////                    .withUser("bbb").password("bbbbb").roles("ADMIN");
////    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                    .antMatchers("/createUserAccount").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/login")
//                    //.failureUrl("/login?login_error=1")
//                    .defaultSuccessUrl("/hiUser")
//                    .permitAll()
//                    .and()
//                .logout()
//                    .logoutSuccessUrl("/login")
//                    .permitAll();
//
//    }

//    @Autowired
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth
//                .userDetailsService(userDetailsService);
////                .jdbcAuthentication()
////                .dataSource(dataSource)
////                .withDefaultSchema()
////                .usersByUsernameQuery("select user_name, password, status from user_account where user_name = ?")
////                .authoritiesByUsernameQuery("select id_user_account, role.role_name from user_account inner join class on role.role_id = user_account.role_id where user_name = ?");
//    }

    @Autowired
    public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService(userDetailsService);
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
                .headers().disable()
                .authorizeRequests()
                .antMatchers("/createUserAccount").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/hiUser")
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)
                    .permitAll();

    }
}
