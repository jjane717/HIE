package org.yijun.hie.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by liuyijun on 14-11-8.
 */


@EnableAutoConfiguration
@Configuration
@ComponentScan("org.yijun.hie")
public class AppEntrance extends WebMvcConfigurerAdapter {

    public static void main(String[] args) throws Exception {
        // Set user password to "password" for demo purposes only
        new SpringApplicationBuilder(AppEntrance.class).properties().run(args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("images/**").addResourceLocations("images/");
        registry.addResourceHandler("css/**").addResourceLocations("css/");
        registry.addResourceHandler("js/**").addResourceLocations("js/");
        registry.addResourceHandler("html/**").addResourceLocations("html/");
    }

//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/login").setViewName("login");
//	}

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        //viewResolver.setPrefix("");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
}