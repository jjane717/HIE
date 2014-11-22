package org.yijun.hie.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

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
        registry.addResourceHandler("jsp/**").addResourceLocations("jsp/");
        registry.addResourceHandler("WEB-INF/**").addResourceLocations("WEB-INF/");
    }

//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/login").setViewName("login");
//	}

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }

    /* *
        * Here we register the Hibernate4Module into an ObjectMapper, then set this custom-configured ObjectMapper
        * to the MessageConverter and return it to be added to the HttpMessageConverters of our application
    */

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        //Registering Hibernate4Module to support lazy objects
        mapper.registerModule(new Hibernate4Module());
        messageConverter.setObjectMapper(mapper);

        //Here we add our custom-configured HttpMessageConverter
        converters.add(messageConverter);
        super.configureMessageConverters(converters);
    }
}