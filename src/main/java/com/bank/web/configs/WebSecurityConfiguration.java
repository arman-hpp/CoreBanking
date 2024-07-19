package com.bank.web.configs;

import com.bank.web.configs.filters.CaptchaAuthenticationFilter;
import com.bank.web.configs.handlers.CustomAccessDeniedHandler;
import com.bank.web.configs.handlers.CustomAuthenticationFailureHandler;
import com.bank.web.configs.handlers.CustomAuthenticationSuccessHandler;
import com.bank.web.configs.handlers.CustomLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import com.bank.services.users.AuthenticationService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {
    public final AuthenticationService _authenticationService;

    public WebSecurityConfiguration(AuthenticationService authenticationService) {
        _authenticationService = authenticationService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/h2/**").permitAll()
                                .requestMatchers("/auth/**", "/register/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/", "/index").permitAll()
                                .requestMatchers(HttpMethod.GET, "/static/**").permitAll()
                                .requestMatchers(HttpMethod.GET,  "/errors/**", "/error/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/auth/login")
                        .successHandler(authenticationSuccessHandler())
                        .failureHandler(authenticationFailureHandler())
                        .permitAll()
                )
                .logout((form) -> form
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/login")
                        .logoutSuccessHandler(logoutSuccessHandler())
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .userDetailsService(_authenticationService);
                //.httpBasic().disable()
                //.exceptionHandling().accessDeniedHandler(accessDeniedHandler())

        http.addFilterBefore(new CaptchaAuthenticationFilter(authenticationFailureHandler()), UsernamePasswordAuthenticationFilter.class);
        //http.sessionManagement().invalidSessionUrl("/auth/login?error=error.auth.credentials.session.expired");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler(_authenticationService);
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler(_authenticationService);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }
}