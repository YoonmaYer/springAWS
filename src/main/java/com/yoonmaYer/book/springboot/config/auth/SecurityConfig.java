package com.yoonmaYer.book.springboot.config.auth;

import com.yoonmaYer.book.springboot.domain.user.Role;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        // 임의의 사용자 정보를 제공하는 메소드 (실제 사용할 때는 데이터베이스에서 사용자 정보를 가져오는 것이 좋습니다.)
        return new InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build()
        );
    }

    @Bean
    public LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
        // 로그인 페이지를 설정하는 메소드
        return new LoginUrlAuthenticationEntryPoint("/login");
    }

    @Configuration
    public static class OAuth2LoginSecurityConfig extends SecurityConfigurerAdapter<Void, HttpSecurity> {

        @Override
        public void configure(HttpSecurity http) {
            // OAuth2 로그인 설정
            http
                    .authorizeRequests(authorizeRequests ->
                            authorizeRequests
                                    .antMatchers("/", "/home").permitAll()
                                    .anyRequest().authenticated()
                    )
                    .oauth2Login(oauth2Login ->
                            oauth2Login
                                    .loginPage("/login")
                    );
        }
    }
}
