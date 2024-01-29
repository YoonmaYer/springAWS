package com.yoonmaYer.book.springboot.config.auth;

import com.yoonmaYer.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console 화면을 사용하기 위한 해당 옵션 끄기
                .csrf().disable()
                .headers().frameOptions().disable()

                // authorizeRequests(): URL별 권한 관리 설정, 이 메서드가 선언되어야 antMatchers 옵션 사용 가능
                .and()
                .authorizeRequests()
                // antMatchers: 권한 관리 대상 지정 옵션, URL, HTTP 메서드별로 관리 가능
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())

                // 설정된 값들 이외 나머지 URL들 나타냄
                .anyRequest().authenticated()

                // 로그아웃 성공 시 / 주소로 이동
                .and()
                .logout()
                .logoutSuccessUrl("/")

                // 소셜 로그인 성공 시 UserService 인터페이스의 구현체 등록
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}
