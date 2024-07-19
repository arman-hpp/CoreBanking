package com.bank.web.configs;

import com.bank.web.extensions.thymeleaf.ThymeleafLayoutInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ThymeleafLayoutInterceptorConfiguration implements WebMvcConfigurer {
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new ThymeleafLayoutInterceptor());
    }
}
